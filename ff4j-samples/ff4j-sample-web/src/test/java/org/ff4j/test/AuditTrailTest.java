package org.ff4j.test;

/*
 * #%L
 * ff4j-sample-web
 * %%
 * Copyright (C) 2013 - 2016 FF4J
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.ff4j.audit.EventQueryDefinition;
import org.ff4j.audit.EventSeries;
import org.ff4j.sample.SimpleFF4jProvider;
import org.junit.Test;

public class AuditTrailTest {
    
    /** Slot for this date. */
    public static final SimpleDateFormat SDFSLOT = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
    
    @Test
    public void testAudit() {
        SimpleFF4jProvider p = new SimpleFF4jProvider();
        p.populateRepository(100);
        
        String value = "12/31/2015 00:00:00 - 12/31/2016 00:00:00";
        EventQueryDefinition edf = new EventQueryDefinition();
        String[] borders = value.split(" - ");
        try {
            Date from = SDFSLOT.parse(borders[0]);
            Date to   = SDFSLOT.parse(borders[1]);
            edf = new EventQueryDefinition(from.getTime(), to.getTime());
            System.out.println(from.getTime());
            System.out.println(to.getTime());
            
        } catch (ParseException e) {
        }
        
        EventSeries auditTrail = p.getFF4j().getEventRepository().getAuditTrail(edf);
        System.out.println(auditTrail.size());
        
        
        
    }

}
