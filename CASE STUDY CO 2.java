class BPlusTreeAnalysis {

    public static void main(String[] args) {

        // Query 1
        String query1 = "user_id = 42 AND timestamp range";

        // Query 2
        String query2 = "user_id = 42";

        // Query 3
        String query3 = "timestamp only";

        System.out.println("MongoDB Compound Index Analysis\n");

        // Query 1 Analysis
        System.out.println("Query 1: " + query1);
        System.out.println("Index Usable: Yes");
        System.out.println("Reason: Uses prefix field user_id and range on timestamp\n");

        // Query 2 Analysis
        System.out.println("Query 2: " + query2);
        System.out.println("Index Usable: Yes");
        System.out.println("Reason: Index is sorted by user_id first\n");

        // Query 3 Analysis
        System.out.println("Query 3: " + query3);
        System.out.println("Index Usable: No (Inefficient)");
        System.out.println("Reason: Timestamp values are spread across all users\n");

        // Cost Estimation
        long totalEntries = 2000000000L;

        double height = Math.log(totalEntries) / Math.log(100);

        int estimatedHeight = (int)Math.ceil(height);

        int cachedLevels = 3;

        int pageReads = estimatedHeight - cachedLevels;

        int additionalLeafPages = 2;

        int totalCost = pageReads + additionalLeafPages;

        System.out.println("Estimated B+ Tree Height = " + estimatedHeight);
        System.out.println("Approximate Cost = " + totalCost + " page reads\n");

        // Conclusion
        System.out.println("Conclusion:");
        System.out.println("Prefix field user_id must be used for efficient index access.");
    }
}