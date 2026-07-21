package com.ds.milvus;

import java.util.List;

import dev.langchain4j.community.model.dashscope.QwenEmbeddingModel;
import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.output.Response;
import io.milvus.v2.client.MilvusClientV2;
import io.milvus.v2.common.IndexParam;
import io.milvus.v2.service.vector.request.SearchReq;
import io.milvus.v2.service.vector.request.data.FloatVec;
import io.milvus.v2.service.vector.response.SearchResp;

/**
 * @author ds
 * @date 2026/7/10
 * @description
 */
public class Demo05Search {
    public static void main(String[] args) {
        MilvusClientV2 client = MilvusHelper.milvusClient();
        QwenEmbeddingModel qwenEmbeddingModel = QwenEmbeddingModel.builder()
                .apiKey(System.getProperty("AI_API_KEY"))
                .modelName("text-embedding-v1")
                .build();

        Response<Embedding> embeddingResponse = qwenEmbeddingModel.embed("线程安全");
        List<Float> floats = embeddingResponse.content().vectorAsList();

        FloatVec queryVector = new FloatVec(floats);
        SearchReq searchReq = SearchReq.builder()
                .metricType(IndexParam.MetricType.COSINE)
                .collectionName("rag_vector_store")
                .data(List.of(queryVector))
                .annsField("embedding")
                .outputFields(List.of("content"))
                .offset(0)
                .limit(3)
                .build();

        SearchResp search = client.search(searchReq);
        List<List<SearchResp.SearchResult>> searchResults = search.getSearchResults();
        for (List<SearchResp.SearchResult> searchResult : searchResults) {
            for (SearchResp.SearchResult result : searchResult) {
                System.out.println(result.getEntity().get("content"));
            }
        }
    }
}
