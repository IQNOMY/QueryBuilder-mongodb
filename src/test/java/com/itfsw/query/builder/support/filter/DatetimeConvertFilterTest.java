/*
 * Copyright (c) 2017.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.itfsw.query.builder.support.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import com.itfsw.query.builder.MongodbQueryBuilderFactory;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.builder.MongodbBuilder;
import com.itfsw.query.builder.support.model.result.MongodbQueryResult;
import com.mongodb.BasicDBList;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 14:16
 * ---------------------------------------------------------------------------
 */
public class DatetimeConvertFilterTest {

    /**
     * 测试参数有list的情况
     */
    @Test
    public void testTypeList() throws IOException {
        final MongodbQueryBuilderFactory factory = new MongodbQueryBuilderFactory();
        final MongodbBuilder builder = factory.builder();
        final String json = FileHelper.getStringFrom("tasks/type-datetime-list.json");
        final MongodbQueryResult result = builder.build(json);

        final BasicDBObject item = (BasicDBObject) ((DBObject) (((BasicDBList) (result.getQuery().get("$and"))).get(1))).get("date");
        Assert.assertTrue(item.get("$gte") instanceof Date);
        Assert.assertTrue(item.get("$lte") instanceof Date);
        Assert.assertEquals("2017-11-01 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.get("$gte")));
        Assert.assertEquals("2017-11-23 00:00:00", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(item.get("$lte")));
    }

}