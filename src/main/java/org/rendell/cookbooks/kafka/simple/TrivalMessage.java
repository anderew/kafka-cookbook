package org.rendell.cookbooks.kafka.simple;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TrivalMessage {

    @Getter private final int key;
    @Getter private final String name;

}
