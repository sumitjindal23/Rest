
/*------------------------------------------------------------------------------------------
** This source is part of the Oracle FLEXCUBE Universal Banking Software Product.
** Copyright © 2016 - 2017  Oracle and/or its affiliates.  All rights reserved.
**                                                                                                                                                                                           
** No part of this work may be reproduced, stored in a retrieval system,
** adopted or transmitted in any form or by any means, electronic, mechanical, photographic, graphic, optic recording or otherwise,
** translated in any language or computer language,
** without the prior written permission of Oracle and/or its affiliates.
** 
** 
** Oracle Financial Services Software Limited.
** Oracle Park, Off Western Express Highway,
** Goregaon (East),
** Mumbai - 400 063, India.
------------------------------------------------------------------------------------------
*/
package com.ofss.config.util;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.Application;

import com.ofss.config.service.CloudConfigService;


/**
 * 
 * This class register the Transaction Service
 *
 */
public class CloudConfigApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(CloudConfigService.class);
        return classes;
    }
}
