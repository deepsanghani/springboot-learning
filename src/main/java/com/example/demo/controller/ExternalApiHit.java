package com.example.demo.controller;

import com.example.demo.entity.GSTINResult;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/external")
public class ExternalApiHit {

    @GetMapping
    public ResponseEntity<String> externalApiHit() {
        long startTime = System.currentTimeMillis();
        String filePath = "C:\\Users\\Deep Sanghani\\Downloads\\Deep_GST_API_test.xlsx";

        try {
            List<GSTINResult> allResults = new ArrayList<>();
            Workbook workbook = new XSSFWorkbook(filePath);
            Sheet sheet = workbook.getSheetAt(0);

            Iterator<Row> iterator = sheet.iterator();
            iterator.next(); // Skip header row

            List<CompletableFuture<List<GSTINResult>>> futures = new ArrayList<>();

            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                String pan = currentRow.getCell(1).getStringCellValue();
                CompletableFuture<List<GSTINResult>> future = searchGSTINByPan(pan, "7678066266");
                futures.add(future);
            }

            CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                    futures.toArray(new CompletableFuture[0])
            );

            CompletableFuture<List<GSTINResult>> allResultFuture = allFutures.thenApply(v ->
                    futures.stream()
                            .map(CompletableFuture::join)
                            .flatMap(List::stream)
                            .collect(java.util.stream.Collectors.toList())
            );

            allResults = allResultFuture.join();

            workbook.close();

            Workbook resultWorkbook = new XSSFWorkbook();
            Sheet resultSheet = resultWorkbook.createSheet("GSTIN Search Results");

            Row headerRow = resultSheet.createRow(0);
            headerRow.createCell(0).setCellValue("PAN");
            headerRow.createCell(1).setCellValue("Contact Number");
            headerRow.createCell(2).setCellValue("GSTIN");
            headerRow.createCell(3).setCellValue("Trade Name");
            headerRow.createCell(4).setCellValue("Status");
            headerRow.createCell(5).setCellValue("Registered Date");
            headerRow.createCell(6).setCellValue("Constitution of Business");
            headerRow.createCell(7).setCellValue("City");
            headerRow.createCell(8).setCellValue("State Code");
            headerRow.createCell(9).setCellValue("Pin Code");

            for (int i = 0; i < allResults.size(); i++) {
                GSTINResult result = allResults.get(i);
                Row row = resultSheet.createRow(i + 1);
                row.createCell(0).setCellValue(result.getPan());
                row.createCell(1).setCellValue(result.getContactNumber());
                row.createCell(2).setCellValue(result.getGstin());
                row.createCell(3).setCellValue(result.getTradeName());
                row.createCell(4).setCellValue(result.getStatus());
                row.createCell(5).setCellValue(result.getRegisteredDate());
                row.createCell(6).setCellValue(result.getConstitutionOfBusiness());
                row.createCell(7).setCellValue(result.getCity());
                row.createCell(8).setCellValue(result.getStateCode());
                row.createCell(9).setCellValue(result.getPinCode());
            }

            String outputFile = "GSTIN_Search_Results.xlsx";
            FileOutputStream outputStream = new FileOutputStream(outputFile);
            resultWorkbook.write(outputStream);
            resultWorkbook.close();
            outputStream.close();

            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println("Data saved to " + outputFile);
            System.out.println("Total execution time: " + totalTime + " milliseconds");
            return ResponseEntity.ok("Data saved to " + outputFile + ". Total execution time: " + totalTime + " milliseconds");
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @Async
    public CompletableFuture<List<GSTINResult>> searchGSTINByPan(String panNo, String contactNo) {
        String url = "https://blog-backend.mastersindia.co/api/v1/custom/search/name_and_pan/?keyword=" + panNo;

        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .build();

        HttpRequest httpRequest = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url))
                .header("authority", "blog-backend.mastersindia.co")
                .header("method", "GET")
                .header("scheme", "https")
                .header("Accept", "application/json, text/plain, */*")
                .header("Accept-Encoding", "gzip, deflate, br, zstd")
                .header("Accept-Language", "en-GB,en-US;q=0.9,en;q=0.8")
                .header("Origin", "https://www.mastersindia.co")
                .header("Referer", "https://www.mastersindia.co/gst-number-search-by-name-and-pan/")
                .header("Sec-Ch-Ua", "\"Chromium\";v=\"122\", \"Not(A:Brand\";v=\"24\", \"Google Chrome\";v=\"122\"")
                .header("Sec-Ch-Ua-Mobile", "?0")
                .header("Sec-Ch-Ua-Platform", "\"Windows\"")
                .header("Sec-Fetch-Dest", "empty")
                .header("Sec-Fetch-Mode", "cors")
                .header("Sec-Fetch-Site", "same-site")
                .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/123.0.0.0 Safari/537.36")
                .build();

        CompletableFuture<HttpResponse<String>> httpResponseFuture = httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString());
        return httpResponseFuture.thenApply(response -> {
            int responseCode = response.statusCode();
            System.out.println("Response code= " + responseCode);
            List<GSTINResult> results = new ArrayList<>();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                String responseBody = response.body();
                JSONObject jsonObject = new JSONObject(responseBody);
                if (jsonObject.getBoolean("success")) {
                    JSONArray dataArray = jsonObject.getJSONArray("data");
                    for (int i = 0; i < dataArray.length(); i++) {
                        JSONObject item = dataArray.getJSONObject(i);
                        if ("Active".equals(item.getString("sts"))) {
                            GSTINResult result = new GSTINResult();
                            result.setPan(panNo);
                            result.setContactNumber(contactNo);
                            result.setGstin(item.getString("gstin"));
                            result.setTradeName(item.getString("tradeNam"));
                            result.setStatus(item.getString("sts"));
                            result.setRegisteredDate(item.getString("rgdt"));
                            if (item.has("ctb")) {
                                result.setConstitutionOfBusiness(item.getString("ctb"));
                            }
                            if (item.has("pradr")) {
                                JSONObject address = item.getJSONObject("pradr").getJSONObject("addr");
                                result.setCity(address.getString("dst"));
                                result.setStateCode(address.getString("stcd"));
                                result.setPinCode(address.getString("pncd"));
                            }
                            results.add(result);
                        }
                    }
                } else {
                    System.out.println("No data found.");
                }
            } else {
                System.out.println("Failed to fetch data. Status code: " + responseCode);
            }
            return results;
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });
    }
}
