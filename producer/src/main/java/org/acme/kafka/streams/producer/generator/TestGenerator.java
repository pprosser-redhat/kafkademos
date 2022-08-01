package org.acme.kafka.streams.producer.generator;

import java.time.Duration;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import io.smallrye.mutiny.Multi;
import io.smallrye.reactive.messaging.kafka.Record;

public class TestGenerator {
    
    static Integer id;

    @Outgoing ("test")
    public Multi<Record<Integer, String>> sendTestMessages (){
        id = Integer.valueOf(0);

        return Multi.createFrom()
                .ticks().every(Duration.ofMillis(500))
                .onOverflow().drop()
                .map(tick ->{
                    System.out.println("producting test message");
                    id++;
                    
                    return Record.of(id, "Message number " + id);

                });
    }

}
