package services.commissions;

import lombok.AllArgsConstructor;


@AllArgsConstructor
public class CommissionService implements ICommissionService {
    private Double percent;
    private Double commission;

    @Override
    public Double calculateCommission(Double amount) {
        return amount * (commission / 100);
    }

    @Override
    public Double calculatePercent(Double amount) {
        return amount * (1 + percent / 100);
    }

    @Override
    public Double calculatePercentsInDays(Double amount, Integer days) {
        return 0D;
    }
}