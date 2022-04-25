package logistics.diagrams;


import com.fasterxml.jackson.annotation.JsonView;
import logistics.diagrams.namedExpressions.*;
import logistics.view.View;
import lombok.Data;

import java.awt.*;
import java.util.List;
import java.util.Random;

@Data
public class NEDiagrams {


    @JsonView(View.Public.class)
    private List<TotalRevenue> totalRevenue;

    @JsonView(View.Public.class)
    private List<TotalSupplyCost> totalSupplyCost;

    @JsonView(View.Public.class)
    private List<TotalInitialCost> totalInitialCost;

    @JsonView(View.Public.class)
    private List<TotalCarryingCost> totalCarryingCost;

    @JsonView(View.Public.class)
    private List<TotalClosingCost> totalClosingCost;

    @JsonView(View.Public.class)
    private List<TotalCO2Emission> totalCO2Emission;

    @JsonView(View.Public.class)
    private List<TotalInboundCost> totalInboundCost;

    @JsonView(View.Public.class)
    private List<TotalOtherCost> totalOtherCost;

    @JsonView(View.Public.class)
    private List<TotalOutboundCost> totalOutboundCost;

    @JsonView(View.Public.class)
    private List<TotalPenalties> totalPenalties;

    @JsonView(View.Public.class)
    private List<TotalProductionCost> totalProductionCost;

    @JsonView(View.Public.class)
    private List<TotalTariffs> totalTariffs;

    @JsonView(View.Public.class)
    private List<TotalTransportationCost> totalTransportationCost;

    @JsonView(View.Public.class)
    private List<TotalCustomerTariffs> totalCustomerTariffs;



    public NEDiagrams() {}

}
