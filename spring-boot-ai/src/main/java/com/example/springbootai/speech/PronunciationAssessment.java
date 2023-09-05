package com.example.springbootai.speech;

import javax.net.ssl.HttpsURLConnection;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Base64;

public class PronunciationAssessment {

    public static void main(String[] args) throws Exception {

        String subscriptionKey = "key"; // replace this with your subscription key
        String region = "eastus"; // replace this with the region corresponding to your subscription key, e.g. westus, eastasia

        // a common wave header, with zero audio length
        // since stream data doesn't contain header, but the API requires header to fetch format information, so you need post this header as first chunk for each query
        final byte[] WaveHeader16K16BitMono = new byte[] { 82, 73, 70, 70, 78, (byte)128, 0, 0, 87, 65, 86, 69, 102, 109, 116, 32, 18, 0, 0, 0, 1, 0, 1, 0, (byte)128, 62, 0, 0, 0, 125, 0, 0, 2, 0, 16, 0, 0, 0, 100, 97, 116, 97, 0, 0, 0, 0 };

        // build pronunciation assessment parameters
        String referenceText = "Good morning.";
        String pronAssessmentParamsJson = "{\"ReferenceText\":\"" + referenceText + "\",\"GradingSystem\":\"HundredMark\",\"Dimension\":\"Comprehensive\"}";
        byte[] pronAssessmentParamsBase64 = Base64.getEncoder().encode(pronAssessmentParamsJson.getBytes("utf-8"));
        String pronAssessmentParams = new String(pronAssessmentParamsBase64, "utf-8");

        // build request (when re-run below code in short time, the connect can be cached and reused behind, with lower connecting time cost)
        URL url = new URL("https://" + region + ".stt.speech.microsoft.com/speech/recognition/conversation/cognitiveservices/v1?language=en-us");
        HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setDoOutput(true);
        connection.setChunkedStreamingMode(0);
        connection.setRequestProperty("Accept", "application/json;text/xml");
        connection.setRequestProperty("Content-Type", "audio/wav; codecs=audio/pcm; samplerate=16000");
        connection.setRequestProperty("Ocp-Apim-Subscription-Key", subscriptionKey);
        connection.setRequestProperty("Pronunciation-Assessment", pronAssessmentParams);

        // send request with chunked data
//        File file = new File("../../goodmorning.pcm");
        File file = new File("/Users/zhangle/code/github/Cognitive-Speech-TTS/PronunciationAssessment/goodmorning.pcm");
        FileInputStream fileStream = new FileInputStream(file);
        byte[] audioChunk = new byte[1024];

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(WaveHeader16K16BitMono);
        int chunkSize = fileStream.read(audioChunk);
        while (chunkSize > 0)
        {
            Thread.sleep(chunkSize / 32); // to simulate human speaking rate
            outputStream.write(audioChunk, 0, chunkSize);
            chunkSize = fileStream.read(audioChunk);
        }

        fileStream.close();
        outputStream.flush();
        outputStream.close();

        long uploadFinishTime = System.currentTimeMillis();

        // receive response
        byte[] responseBuffer = new byte[connection.getContentLength()];
        InputStream inputStream = connection.getInputStream();
        int offset = 0;
        int readBytes = inputStream.read(responseBuffer);
        while (readBytes != -1)
        {
            offset += readBytes;
            readBytes = inputStream.read(responseBuffer, offset, responseBuffer.length - offset);
        }

        String result = new String(responseBuffer, "utf-8"); // the result is a JSON, you can parse it with a JSON library

        System.out.println("Pronunciation assessment result:\n");
        System.out.println(result);

        long getResponseTime = System.currentTimeMillis();
        System.out.println("\nLatency: " + (getResponseTime - uploadFinishTime) + "ms");
    }
}
