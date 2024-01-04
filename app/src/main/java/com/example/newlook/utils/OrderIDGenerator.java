package com.example.newlook.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class OrderIDGenerator {
    public static String generateOrderID() {
        String uniqueID = UUID.randomUUID().toString();

        // Format the current timestamp
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String timestamp = dateFormat.format(new Date());

        // Concatenate the timestamp with the unique ID to create the order ID
        String orderID = "ORD_" + timestamp + "_" + uniqueID.replaceAll("-", "").substring(0, 6);

        return orderID;
    }


}
