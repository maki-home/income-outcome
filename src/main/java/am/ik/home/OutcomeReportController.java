package am.ik.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
public class OutcomeReportController {
    @Autowired
    OutcomeRepository outcomeRepository;

    @RequestMapping(path = "api/outcomes/reportByDate")
    List<Outcome.SummaryByDate> reportByDate(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("fromDate") LocalDate fromDate,
                                             @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("toDate") LocalDate toDate) {
        return outcomeRepository.findSummaryByDate(fromDate, toDate);
    }

    @RequestMapping(path = "api/outcomes/reportByParentCategory")
    List<Outcome.SummaryByParentCategory> reportByParentCategory(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("fromDate") LocalDate fromDate,
                                                                 @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) @RequestParam("toDate") LocalDate toDate) {
        return outcomeRepository.findSummaryByParentCategory(fromDate, toDate);
    }
}
