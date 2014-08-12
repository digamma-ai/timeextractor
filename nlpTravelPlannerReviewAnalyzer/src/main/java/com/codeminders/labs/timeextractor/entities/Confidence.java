package com.codeminders.labs.timeextractor.entities;

import edu.stanford.nlp.ling.CoreAnnotation;

public class Confidence implements CoreAnnotation<Long> {
    
    public Class<Long> getType() {
        return Long.class;
    }

}