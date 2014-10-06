package com.codeminders.labs.timeextractor.dto;

import com.codeminders.labs.timeextractor.temporal.entities.Temporal;

public class DTODurationInterval implements DTOTemporal {

    private DTODuration durationFrom;
    private DTODuration durationTo;

    public DTODurationInterval(Temporal durationInterval) {
        durationFrom = new DTODuration(durationInterval.getDurationInterval().getDurationFrom());
        durationTo = new DTODuration(durationInterval.getDurationInterval().getDurationTo());

    }

    public DTODuration getDurationFrom() {
        return durationFrom;
    }

    public void setDurationFrom(DTODuration durationFrom) {
        this.durationFrom = durationFrom;
    }

    public DTODuration getDurationTo() {
        return durationTo;
    }

    public void setDurationTo(DTODuration durationTo) {
        this.durationTo = durationTo;
    }

}
