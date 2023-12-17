package com.mongo.mongoWrapper.Wrapper;

import com.mongo.mongoWrapper.connection.MongoConnectionManager;
import com.mongodb.Function;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.extern.slf4j.Slf4j;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

@Slf4j
public class MongoDataInsert<T> {

//    private final MongoDatabase database;
//
//    public MongoDataInsert(MongoDatabase database) {
//        this.database = database;
//    }



//    public void persistDatasourceInDbBulk(final TransmissionRequest transmissionRequest,
//                                          final Set<String> targets,
//                                          long fileTimeStamp) {
////        List<com.gaian.cdf.model.request.DataSource> dataSources = transmissionRequest.getDataSources();
//        List<Document> documents = new ArrayList<>();
//
//        for (DataSource fileSource : transmissionRequest.getDataSources()) {
//            if (log.isDebugEnabled()) {
//                log.debug("data source :: {}", fileSource);
//            }
//
//            String fileName = fileSource.getFileName();
//            String filePath = determineFilePath(fileSource);
//
//            try {
//                statusService.setFileRequestStatus(transmissionRequest.getRequestId(),
//                        Collections.singletonList(fileSource.getFileId()), Status.PROCESSING, "");
//
//                persistClientFile(transmissionRequest, targets, fileName, filePath, fileTimeStamp, fileSource);
//                persistPhysicalFile(transmissionRequest, fileSource, filePath, fileTimeStamp);
//
//                statusService.setFileRequestStatus(transmissionRequest.getRequestId(),
//                        Collections.singletonList(fileSource.getFileId()), Status.SAVED, "");
//
//                // Create a Document and add it to the list for bulk insertion
//                Document document = new Document()
//                        .append("fileName", fileName)
//                        .append("filePath", filePath)
//                        .append("status", "SAVED")
//                        .append("fileTimeStamp", fileTimeStamp);
//
//                // Add more fields based on your DataSource structure
//
//                documents.add(document);
//            } catch (Exception e) {
//                log.warn("Error processing data source: {}", fileSource, e);
//                // Handle the exception as needed
//            }
//        }
//
//        // Perform bulk insert into MongoDB
//        persistDataInBulk(documents);
//    }
//
//    private void persistDataInBulk(List<Document> documents) {
//        MongoCollection<Document> collection = database.getCollection("your_collection_name");
//        collection.insertMany(documents);
//    }
//
//    private String determineFilePath(DataSource fileSource) {
//        String path = "";
//        if (fileSource.getUri().startsWith("sharedLocation")) {
//            log.info("request is of sharedLocation uri");
//            path = "/".concat(FilenameUtils.getPath(getLocationFromUri(fileSource.getUri())));
//        }
//        return path;
//    }

    @Value("${spring.data.mongodb.database}")
    private String databaseName;

    private String collectionName = "fileMetaData";

    private final MongoConnectionManager mongoConnection;
    private final Function<T, Document> documentMapper;
    private final Predicate<T> errorCondition;

    public MongoDataInsert(Function<T, Document> documentMapper,
                               Predicate<T> errorCondition,
                           MongoConnectionManager mongoConnection) {
        this.documentMapper = documentMapper;
        this.errorCondition = errorCondition;
        this.mongoConnection = mongoConnection;
    }

    public void persistDataInBulkGeneric(List<T> data) { //dataverse , tflib
        List<Document> documents = new ArrayList<>();

        for (T item : data) {
            try {
                if (errorCondition.test(item)) {
                    // Handle error condition
                    log.warn("Error condition for item: {}", item);
                    continue;
                }
                // Map item to Document
                Document document = documentMapper.apply(item);
                documents.add(document);
            } catch (Exception e) {
                log.warn("Error processing item: {}", item, e);
                e.printStackTrace();
            }
        }

        // Perform bulk insert into MongoDB
        persistDataInBulk(documents);
    }

    private void persistDataInBulk(List<Document> documents) {
        MongoClient client = mongoConnection.mongoClient();
        MongoDatabase db = client.getDatabase(databaseName);
        MongoCollection<Document> collection = db.getCollection(collectionName);
        collection.insertMany(documents);
    }


    // Define getLocationFromUri and other necessary methods as needed
}
