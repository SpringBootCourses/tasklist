package com.example.tasklist.config;

import graphql.GraphQLContext;
import graphql.execution.CoercedVariables;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class LocalDateTimeCoercing implements Coercing<LocalDateTime, String> {

    @Override
    public @Nullable
    String serialize(
            @NotNull final Object dataFetcherResult,
            @NotNull final GraphQLContext graphQLContext,
            @NotNull final Locale locale
    ) throws CoercingSerializeException {
        SimpleDateFormat formatter
                = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                Locale.ENGLISH);
        return formatter.format(
                Date.from(((LocalDateTime) dataFetcherResult)
                        .atZone(ZoneId.systemDefault())
                        .toInstant())
        );
    }

    @Override
    public @Nullable
    LocalDateTime parseValue(
            @NotNull final Object input,
            @NotNull final GraphQLContext graphQLContext,
            @NotNull final Locale locale
    ) throws CoercingParseValueException {
        return LocalDateTime.parse((String) input);
    }

    @Override
    public @Nullable
    LocalDateTime parseLiteral(
            @NotNull final Value<?> input,
            @NotNull final CoercedVariables variables,
            @NotNull final GraphQLContext graphQLContext,
            @NotNull final Locale locale
    ) throws CoercingParseLiteralException {
        return LocalDateTime.parse(((StringValue) input).getValue());
    }

}
