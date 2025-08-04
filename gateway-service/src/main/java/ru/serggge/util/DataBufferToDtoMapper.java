package ru.serggge.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ru.serggge.dto.RegisterRequest;

import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class DataBufferToDtoMapper {

    private final ObjectMapper objectMapper;

//    public <T> Mono<T> mapToMonoDto(Flux<DataBuffer> dataBufferFlux, Class<T> dtoClass) {
//        return DataBufferUtils.join(dataBufferFlux) // Join all DataBuffers into a single Mono<DataBuffer>
//                              .flatMap(dataBuffer -> {
//                                  try {
//                                      byte[] bytes = new byte[dataBuffer.readableByteCount()];
//                                      dataBuffer.read(bytes);
//                                      DataBufferUtils.release(dataBuffer); // Release the DataBuffer
//                                      return Mono.just(objectMapper.readValue(bytes, dtoClass)); // Deserialize bytes to DTO
//                                  } catch (Exception e) {
//                                      DataBufferUtils.release(dataBuffer); // Ensure release on error
//                                      return Mono.error(new RuntimeException("Error mapping DataBuffer to DTO", e));
//                                  }
//                              });
//    }

    public Mono<RegisterRequest> convert(Flux<DataBuffer> dataBuffers) {
        return DataBufferUtils.join(dataBuffers)
                              .flatMap(dataBuffer -> {
                                  try {
                                      // Convert DataBuffer to byte array
                                      byte[] bytes = new byte[dataBuffer.readableByteCount()];
                                      dataBuffer.read(bytes);
                                      DataBufferUtils.release(dataBuffer); // Release the DataBuffer
                                      // Deserialize byte array to DTO
                                      RegisterRequest dto = objectMapper.readValue(bytes, RegisterRequest.class);
                                      return Mono.just(dto);
                                  } catch (Exception e) {
                                      return Mono.error(new RuntimeException("Error converting DataBuffer to DTO", e));
                                  }
                              });
    }
}
