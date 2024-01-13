package com.daviddev16.core.util;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.function.Function;

/**
 * Utilitários para interação com tipos de Data
 * */
public final class DateUtil {

    public static Date criarDateComOperador(LocalDateTime dateTimeInicial,
                                            Function<LocalDateTime, LocalDateTime> operadorDateTime) {

        final LocalDateTime dataHoraExpiracao = (operadorDateTime != null) ?
                operadorDateTime.apply(dateTimeInicial) : dateTimeInicial;

        final Instant instant = dataHoraExpiracao
                .atZone(ZoneId.systemDefault()).toInstant();

        return Date.from(instant);
    }

    public static LocalDateTime coverterDateToLocalDateTime(Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
