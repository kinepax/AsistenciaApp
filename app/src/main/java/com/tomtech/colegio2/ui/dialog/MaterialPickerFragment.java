package com.tomtech.colegio2.ui.dialog;

import androidx.annotation.NonNull;
import com.google.android.material.datepicker.CalendarConstraints;
import com.google.android.material.datepicker.MaterialDatePicker;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
public class MaterialPickerFragment {

    public static MaterialDatePicker<Long> createCustomDatePicker(LocalDate fecha, ZoneId zonaHoraria) {
        CalendarConstraints.Builder constraintsBuilder = new CalendarConstraints.Builder();

        constraintsBuilder.setValidator(new CalendarConstraints.DateValidator() {
            @Override
            public boolean isValid(long date) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTimeInMillis(date);
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                return dayOfWeek != Calendar.SATURDAY - 1 && dayOfWeek != Calendar.SATURDAY;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(@NonNull android.os.Parcel parcel, int i) {

            }
        });

        MaterialDatePicker.Builder<Long> builder = MaterialDatePicker.Builder.datePicker();
        builder.setCalendarConstraints(constraintsBuilder.build());

        // Convertir LocalDate a LocalDateTime en la zona horaria deseada
        LocalDateTime localDateTime = fecha.atStartOfDay().atZone(zonaHoraria).toLocalDateTime();

        // Convertir LocalDateTime a Date en la zona horaria UTC
        Instant instant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        Date utcDate = Date.from(instant);

        // Configurar la fecha inicial en el constructor de MaterialDatePicker
        builder.setSelection(utcDate.getTime());

        return builder.build();
    }

    public static LocalDate convertDateToLocalDate(Date date, ZoneId zonaHoraria) {
        Instant instant = Instant.ofEpochMilli(date.getTime());
        LocalDateTime localDateTime = instant.atZone(ZoneId.of("UTC")).toLocalDateTime();
        return localDateTime.atZone(zonaHoraria).toLocalDate();
    }
}