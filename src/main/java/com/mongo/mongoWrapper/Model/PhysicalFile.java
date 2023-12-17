package com.mongo.mongoWrapper.Model;

import com.fasterxml.jackson.annotation.JsonProperty;
//import com.gaian.pitcher.data.model
//import com.gaian.cdf.model.enums.Priority;
import com.gaian.cdf.server.model.request.Targeting;
//import com.gaian.services.engagement.model.request.channel.fax.Priority;
import com.gaian.pitcher.data.model.MetaDataDao;
import com.gaian.voxa.provider.api.constants.Priority;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.JavaPKMSTServerCodegen", date = "2021-05-20T03:28:49.575Z")
@CompoundIndexes({@CompoundIndex(def = "{'id':1,'tenantId':1}", name = "file-meta-data-1")})
@Document(collection = "fileMetaData")
@Data
@ToString
public class PhysicalFile implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    /**
     * Unique mongo id
     **/
//    @ApiModelProperty(example = "608bfb4e1b89300008af4b79", value = "Unique mongo id")
    @JsonProperty("_id")
    @Id
    private String id;

    /**
     * Id of the tenant.
     **/
//    @ApiModelProperty(example = "608bfb4e1b89300008af4b75", value = "Id of the tenant.")
    @JsonProperty("tenantId")
    private String tenantId;

    /**
     * Id of file
     **/
    @Indexed
//    @ApiModelProperty(example = "608bfb4e1b89300008af4b75", value = "Id of file")
    @JsonProperty("fileId")
    private String fileId;

    /**
     * Name of file
     *
     * @return fileName
     **/
//    @ApiModelProperty(example = "filename", value = "Name of file")
    @JsonProperty("fileName")
    private String fileName;

    /**
     * Time when file should be removed
     **/
//    @ApiModelProperty(example = "1326244368", value = "Time when file should be removed")
    @JsonProperty("expiryAt")
    private long expiryAt;

    /**
     * Should file be unzipped or not
     **/
//    @ApiModelProperty(example = "false", value = "Should file be unzipped or not")
    @JsonProperty("extract")
    private boolean extract;

    /**
     * Path where file is stored on server
     **/
//    @ApiModelProperty(example = "/var/cat/file.zip", value = "Path where file is stored on server")
    @JsonProperty("serverDir")
    private String serverDir;

    /**
     * Name of file
     **/
//    @ApiModelProperty(example = "file.zip", value = "Name of file")
    @JsonProperty("serverFileName")
    private String serverFileName;

    /**
     * Path where file should be stored
     **/
//    @ApiModelProperty(example = "/var/gaian/xyz", value = "Path where file should be stored")
    @JsonProperty("destinationPath")
    private String destinationPath;

    /**
     * Priority level
     **/
//    @ApiModelProperty(example = "CRITICAL or HIGH or MEDIUM or LOW", value = "Priority level")
    @JsonProperty("priority")
    private Priority priority;

    /**
     * Get metaData
     **/
//    @ApiModelProperty(value = "")
    @JsonProperty("metaData")
    private MetaDataDao metaData;

    /**
     * Get targeting
     **/
//    @ApiModelProperty(value = "")
    @JsonProperty("targeting")
    private Targeting targeting;

    /**
     * Id of the transaction.
     **/
//    @ApiModelProperty(example = "608bfb4e1b89300008af4b75", value = "transactionId")
    //@JsonProperty("transactionId")
    private String transactionId;

//    @ApiModelProperty(value = "")
    @JsonProperty("serviceId")
    private String serviceId;

}
