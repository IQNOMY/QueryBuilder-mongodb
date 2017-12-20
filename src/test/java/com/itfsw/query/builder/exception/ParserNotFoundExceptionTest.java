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

package com.itfsw.query.builder.exception;

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

import com.itfsw.query.builder.MongodbQueryBuilderFactory;
import com.itfsw.query.builder.other.CustomMongodbParser;
import com.itfsw.query.builder.other.FileHelper;
import com.itfsw.query.builder.support.builder.MongodbBuilder;
import com.itfsw.query.builder.support.model.result.MongodbQueryResult;
import com.itfsw.query.builder.support.utils.spring.StringUtils;

/**
 * ---------------------------------------------------------------------------
 *
 * ---------------------------------------------------------------------------
 * @author: hewei
 * @time:2017/11/3 13:54
 * ---------------------------------------------------------------------------
 */
public class ParserNotFoundExceptionTest {

    /**
     * 测试parser-mongodb 找不到
     */
    @Test(expected = ParserNotFoundException.class)
    public void testMongodbParserNotFound() throws IOException {
        final MongodbQueryBuilderFactory factory = new MongodbQueryBuilderFactory();
        final MongodbBuilder builder = factory.builder();

        final String json = FileHelper.getStringFrom("tasks/custom-operator.json");
        builder.build(json);
    }

    /**
     * 测试parser-mongodb 找不到(添加自定义)
     */
    @Test
    public void testMongodbParserNotFoundAndAdd() throws IOException {
        final MongodbQueryBuilderFactory factory = new MongodbQueryBuilderFactory();
        factory.addRuleParser(new CustomMongodbParser());
        final MongodbBuilder builder = factory.builder();

        final String json = FileHelper.getStringFrom("tasks/custom-operator.json");
        final MongodbQueryResult result = builder.build(json);

        Assert.assertEquals(
                StringUtils.trimAllWhitespace(result.toString()),
                "{\"$or\":[{\"username\":{\"$ne\":\"Mistic\"}}]}"
        );
    }
}