import {CDate} from "../../../../model/util/cDate";

export enum WorkingHoursChartViewMode {
  WEEK,
  MONTH,
  QUARTER,
  YEAR
}

export class WorkingHoursChartViewModeUtil {

  static getDateTimeRange(viewMode: WorkingHoursChartViewMode): WorkingHourDateRange{
    switch (viewMode){
      case WorkingHoursChartViewMode.WEEK: return WorkingHourDateRangeUtil.of(CDate.now().getLastMonday(), CDate.now().getNextSunday());
      case WorkingHoursChartViewMode.MONTH: return WorkingHourDateRangeUtil.of(CDate.now().getLastMonthStart(), CDate.now().getNextMonthStart());
      case WorkingHoursChartViewMode.QUARTER: return WorkingHourDateRangeUtil.of(CDate.now().getLastQuarterStart(), CDate.now().getNextQuarterStart());
      case WorkingHoursChartViewMode.YEAR: return WorkingHourDateRangeUtil.of(CDate.now().getLastYearStart(), CDate.now().getNextYearStart());
    }
  }
}

export class WorkingHourDateRangeUtil {

  static getDefaultDateRange(): WorkingHourDateRange {
    return new class implements WorkingHourDateRange {
      end: CDate = CDate.now().getNextSunday();
      start: CDate = CDate.now().getLastMonday();
    }
  }

  static of(start: CDate, end: CDate){
    return new class implements WorkingHourDateRange {
      end: CDate = end;
      start: CDate = start;

    }
  }
}
export interface WorkingHourDateRange {
  start: CDate,
  end: CDate
}
