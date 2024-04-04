package models.banks;


import lombok.Builder;

@Builder
public record BankModel(
        String name,
        Double percent,
        Double commission,
        Double maxSum
)
{
    public BankModel changePercent(Double percent) {
        return builder()
                .name(name())
                .percent(percent)
                .commission(commission())
                .maxSum(maxSum())
                .build();
    }

    public BankModel changeCommission(Double commission) {
        return builder()
                .name(name())
                .percent(percent())
                .commission(commission)
                .maxSum(maxSum())
                .build();
    }
}
