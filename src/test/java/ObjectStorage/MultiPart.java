package ObjectStorage;

import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MultiPart {

    @Test
    void MultiPartUploadTest() {
        final String endPoint = "https://kr.object.ncloudstorage.com";
        final String regionName = "kr-standard";
        final String accessKey =  System.getenv("ACCESS_KEY"); //"ACCESS_KEY";
        final String secretKey = System.getenv("SECRET_KEY"); //"SECRET_KEY";

        // S3 client
        final AmazonS3 s3 = AmazonS3ClientBuilder.standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(endPoint, regionName))
                .withCredentials(new AWSStaticCredentialsProvider(new BasicAWSCredentials(accessKey, secretKey)))
                .build();

        String bucketName = "s3-sdk-bucket";
        String objectName = "sample-large-object";

        // delete
        try {
            s3.deleteObject(bucketName, objectName);
        } catch (Exception e) {
            e.printStackTrace();
        }

        int partSize = 10 * 1024 * 1024;

        try {
            // initialize and get upload ID
            InitiateMultipartUploadResult initiateMultipartUploadResult = s3.initiateMultipartUpload(new InitiateMultipartUploadRequest(bucketName, objectName));
            String uploadId = initiateMultipartUploadResult.getUploadId();

            // upload parts
            List<PartETag> partETagList = new ArrayList<PartETag>();

            String data = initializeData();

            int partNum = 1;
            for (int i = 0 ; i < 10; i++) {
                byte[] bytes = data.getBytes(StandardCharsets.UTF_8);
                int chunkSize = Math.min(partSize, bytes.length);

                UploadPartRequest uploadPartRequest = new UploadPartRequest()
                        .withBucketName(bucketName)
                        .withKey(objectName)
                        .withUploadId(uploadId)
                        .withPartNumber(partNum++) // partNumber must be integer between 1 and 10000
                        .withPartSize(chunkSize)
                        .withInputStream(new ByteArrayInputStream(bytes));

                UploadPartResult uploadPartResult = s3.uploadPart(uploadPartRequest);
                partETagList.add(uploadPartResult.getPartETag());
            }

//            int fileOffset = 0;
//            for (int i = 1; fileOffset < contentLength; i++) {
//                partSize = Math.min(partSize, (contentLength - fileOffset));
//
//                UploadPartRequest uploadPartRequest = new UploadPartRequest()
//                        .withBucketName(bucketName)
//                        .withKey(objectName)
//                        .withUploadId(uploadId)
//                        .withPartNumber(i)
//                        .withFile(file)
//                        .withFileOffset(fileOffset)
//                        .withPartSize(partSize);

//                UploadPartResult uploadPartResult = s3.uploadPart(uploadPartRequest);
//                partETagList.add(uploadPartResult.getPartETag());
//
//                fileOffset += partSize;
//            }

            // abort
            // s3.abortMultipartUpload(new AbortMultipartUploadRequest(bucketName, objectName, uploadId));

            // complete
            CompleteMultipartUploadResult completeMultipartUploadResult = s3.completeMultipartUpload(new CompleteMultipartUploadRequest(bucketName, objectName, uploadId, partETagList));
            System.out.println("completeMultipartUploadResult = " + completeMultipartUploadResult);

            // get object
            try {
                S3Object object = s3.getObject(new GetObjectRequest(bucketName, objectName));
                BufferedReader reader = new BufferedReader(new InputStreamReader(object.getObjectContent()));

                int lintCount = 0;
                String line;
                while (lintCount < 10) {
                    if ((line = reader.readLine()) != null) {
                        System.out.println("line = " + line);
                    }
                    lintCount++;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (SdkClientException e) {
            e.printStackTrace();
        }
    }

    public String initializeData() {
        List<List<Object>> tempData = new ArrayList<>();
        for (int i = 0; i < 10000; i ++) {
            tempData.add(new ArrayList<>());
            for (int j = 0; j < 10000; j ++) {
                tempData.get(i).add(i + j);
            }
        }

        StringBuilder stringBuilder = new StringBuilder(10000 * 10000);
        for (List<Object> temp : tempData) {
            stringBuilder.append(temp.toString()).append("\n");
        }
        return stringBuilder.toString();
    }
}
