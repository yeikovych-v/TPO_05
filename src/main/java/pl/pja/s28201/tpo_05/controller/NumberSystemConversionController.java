package pl.pja.s28201.tpo_05.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NumberSystemConversionController {

    @PostMapping("/ns/conv")
    @ResponseBody
    public String returnConvertedNumber(@RequestParam(name = "val") String val,
                                        @RequestParam(name = "from") int from,
                                        @RequestParam(name = "to") int to) {

        int decimal = Integer.parseInt(val, from);

        String converted = Integer.toString(decimal, to);

        return "<h3> Your Value in '" + from +
                "' digit system is, in '" + to +
                "' digit system: <h3><h4>" + converted + "</h4>" +
                "<h5>" + "In Binary: " + Integer.toBinaryString(decimal) + "</h5>" +
                "<h5>" + "In Octal: " + Integer.toOctalString(decimal) + "</h5>" +
                "<h5>" + "In Decimal: " + decimal + "</h5>" +
                "<h5>" + "In Hexadecimal: " + Integer.toHexString(decimal) + "</h5>";
    }
}
