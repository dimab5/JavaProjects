package services.commissions;

public interface ICommissionService {
    Double calculateCommission(Double amount);
    Double calculatePercent(Double amount);
    Double calculatePercentsInDays(Double amount, Integer days);
}
