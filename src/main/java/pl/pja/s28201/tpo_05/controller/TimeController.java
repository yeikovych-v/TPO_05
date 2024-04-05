package pl.pja.s28201.tpo_05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
public class TimeController {

    private static final String DEFAULT_PATTERN = "hh:mm:ss.SSSS yyyy/MM/dd";
    private static final ZoneId DEFAULT_ZONE = ZoneId.systemDefault();

    @GetMapping("/current-time")
    @ResponseBody
    public String returnCurrentTime(@RequestParam(name = "zoneId", required = false) String zoneName,
                                    @RequestParam(name = "dtFormat", required = false, defaultValue = "hh:mm:ss.SSSS yyyy/MM/dd") String dateTimeFormat) {
        String zoneErrMsg = "";
        String formatErrMsg = "";

        DateTimeFormatter defaultFormatter = parseDateTimeFormatter(dateTimeFormat);

        if (zoneName != null && !zoneName.isEmpty()) {
            Optional<ZoneId> parsedZone = parseZone(zoneName);
            if (parsedZone.isPresent()) {
                return "<h3>" + zoneName + " Format: </h3>" + ZonedDateTime.now().format(defaultFormatter.withZone(parsedZone.get()));
            }
            zoneErrMsg = "Unable to parse given zoneId.";
        }

        return "<h2>" + zoneErrMsg + "</h2>" +
                "<h2>" + formatErrMsg + "</h2>" +
                "<h3> Default Format: </h3>" +
                ZonedDateTime.now().format(defaultFormatter.withZone(DEFAULT_ZONE));
    }

    private DateTimeFormatter parseDateTimeFormatter(String dateTimeFormatPattern) {
        try {
            return DateTimeFormatter.ofPattern(dateTimeFormatPattern);
        } catch (IllegalArgumentException e) {
            return DateTimeFormatter.ofPattern(DEFAULT_PATTERN);
        }
    }

    private Optional<ZoneId> parseZone(String zoneName) {
        try {
            return Optional.of(ZoneId.of(zoneName));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @GetMapping("/current-year")
    @ResponseBody
    public String returnCurrentYear() {
        return String.valueOf(LocalDateTime.now().getYear());
    }
}
