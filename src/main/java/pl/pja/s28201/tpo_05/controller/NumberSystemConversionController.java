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

        StringBuilder conversionHeader = new StringBuilder();

        conversionHeader.append("<h3> Your Value in '").append(from)
                .append("' digit system is, in '").append(to)
                .append("' digit system: <h3><h4>").append(converted).append("</h4>");

        conversionHeader.append("<h5>").append("In Binary: ").append(Integer.toBinaryString(decimal)).append("</h5>");

        conversionHeader.append("<h5>").append("In Octal: ").append(Integer.toOctalString(decimal)).append("</h5>");

        conversionHeader.append("<h5>").append("In Decimal: ").append(decimal).append("</h5>");

        conversionHeader.append("<h5>").append("In Hexadecimal: ").append(Integer.toHexString(decimal)).append("</h5>");

        return conversionHeader.toString();
    }
}
