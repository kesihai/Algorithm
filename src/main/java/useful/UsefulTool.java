package useful;

public class UsefulTool {
  public static void calculateLoan(
      int loanMoney, int year, double loanRatePerYear) {
    final double loanRateMonth = loanRatePerYear / 100 / 12;

    double totalSubmit = 0;
    double totalCapitalSubmit = 0;
    double allMonry = totalMoney(loanMoney, year, loanRatePerYear);
    System.out.println("月数\t应交\t本金\t利息\t累计偿还本金\t累计偿还\t还剩本金\t还剩本金加利息\t");
    for (int i = 0; i < year * 12; i++) {
      int curMonth = i + 1;
      double capital = loanMoney * 1.0 / year / 12;
      double rate = (loanMoney - totalCapitalSubmit) * (loanRateMonth);
      totalCapitalSubmit += capital;
      totalSubmit += capital + rate;

      System.out.printf("%s\t%.0f\t%.0f\t%.0f\t%.0f\t\t%.0f\t%.0f\t%.0f\n",
          curMonth, capital + rate, capital, rate, totalCapitalSubmit,
          totalSubmit, loanMoney - totalCapitalSubmit, allMonry - totalSubmit);
    }
  }

  public static double totalMoney(double loanMoney, int year,
      double loanRatePerYaer) {
    final double loadRateMonth = loanRatePerYaer / 100 / 12;
    double totalCapitalSubmit = 0;
    double totalSubmit = 0;
    for (int i = 0; i < year * 12; i++) {
      double capital = loanMoney * 1.0 / year / 12;
      double rate = (loanMoney - totalCapitalSubmit) * loadRateMonth;
      totalCapitalSubmit += capital;
      totalSubmit += capital + rate;
    }
    return totalSubmit;
  }



  public static void main(String[] args) {
    UsefulTool.calculateLoan(330000, 10, 4.9);
  }
}

/*
159357
 */