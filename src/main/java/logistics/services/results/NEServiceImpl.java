package logistics.services.results;

import logistics.DAO.result.all.NEDAO;
import logistics.diagrams.NEDiagrams;
import logistics.diagrams.namedExpressions.*;
import logistics.entityes.results.NamedExpression;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class NEServiceImpl implements NEService {

    private NEDAO nEDAO;

    public NEServiceImpl(NEDAO nEDAO){
        this.nEDAO = nEDAO;
    }

    @Override
    public Long save(NamedExpression namedExpression) {
        return nEDAO.save(namedExpression).getId();
    }

    @Override
    public void delete(NamedExpression namedExpression) {
        nEDAO.delete(namedExpression);
    }

    @Override
    public List<NamedExpression> getByIteration(Integer iteration) {
        return nEDAO.getNamedExpressionsByIteration(iteration);
    }

    @Override
    public Long update(NamedExpression namedExpression) {
        NamedExpression namedExpression1 = nEDAO.getById(namedExpression.getId());
        namedExpression1.setIteration(namedExpression.getIteration());
        namedExpression1.setExpression_name(namedExpression.getExpression_name());
        namedExpression1.setValue(namedExpression.getValue());
        return nEDAO.save(namedExpression1).getId();
    }

    @Override
    public NEDiagrams getDataset() {
        List<NamedExpression> data = nEDAO.findAll();
        NEDiagrams neDiagrams = new NEDiagrams();
        while (data.size() > 0) {

            String label = data.get(0).getExpression_name();

            List<NamedExpression> datasets = data.stream()
                    .filter(dataset_ ->
                            dataset_.getExpression_name()
                                    .equals(label))
                    .collect(Collectors.toList());


            if(label.equals("Total Revenue")) {

                List<TotalRevenue> totalRevenues = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalRevenue totalRevenue = new TotalRevenue();
                    totalRevenue.setData(data_list);
                    totalRevenue.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalRevenues.add(totalRevenue);
                }

                neDiagrams.setTotalRevenue(totalRevenues);
                data.removeAll(datasets);
            }

            if(label.equals("Total Production Cost")) {

                List<TotalProductionCost> totalProductionCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalProductionCost totalProductionCost = new TotalProductionCost();
                    totalProductionCost.setData(data_list);
                    totalProductionCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalProductionCosts.add(totalProductionCost);
                }

                neDiagrams.setTotalProductionCost(totalProductionCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Closing Cost")) {

                List<TotalClosingCost> totalClosingCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalClosingCost totalClosingCost = new TotalClosingCost();
                    totalClosingCost.setData(data_list);
                    totalClosingCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalClosingCosts.add(totalClosingCost);
                }

                neDiagrams.setTotalClosingCost(totalClosingCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total CO2 Emission")) {

                List<TotalCO2Emission> totalCO2Emissions = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalCO2Emission totalCO2Emission = new TotalCO2Emission();
                    totalCO2Emission.setData(data_list);
                    totalCO2Emission.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalCO2Emissions.add(totalCO2Emission);
                }

                neDiagrams.setTotalCO2Emission(totalCO2Emissions);
                data.removeAll(datasets);
            }

            if(label.equals("Total Initial Cost")) {

                List<TotalInitialCost> totalInitialCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalInitialCost totalInitialCost = new TotalInitialCost();
                    totalInitialCost.setData(data_list);
                    totalInitialCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalInitialCosts.add(totalInitialCost);
                }

                neDiagrams.setTotalInitialCost(totalInitialCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Other Cost")) {

                List<TotalOtherCost> totalOtherCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalOtherCost totalOtherCost = new TotalOtherCost();
                    totalOtherCost.setData(data_list);
                    totalOtherCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalOtherCosts.add(totalOtherCost);
                }

                neDiagrams.setTotalOtherCost(totalOtherCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Supply Cost")) {

                List<TotalSupplyCost> totalSupplyCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalSupplyCost totalSupplyCost = new TotalSupplyCost();
                    totalSupplyCost.setData(data_list);
                    totalSupplyCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalSupplyCosts.add(totalSupplyCost);
                }

                neDiagrams.setTotalSupplyCost(totalSupplyCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Carrying Cost")) {

                List<TotalCarryingCost> totalCarryingCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalCarryingCost totalCarryingCost = new TotalCarryingCost();
                    totalCarryingCost.setData(data_list);
                    totalCarryingCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalCarryingCosts.add(totalCarryingCost);
                }

                neDiagrams.setTotalCarryingCost(totalCarryingCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Tariffs")) {

                List<TotalTariffs> totalTariffs_s = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalTariffs totalTariffs = new TotalTariffs();
                    totalTariffs.setData(data_list);
                    totalTariffs.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalTariffs_s.add(totalTariffs);
                }

                neDiagrams.setTotalTariffs(totalTariffs_s);
                data.removeAll(datasets);
            }


        if(label.equals("Total Customer Tariffs")) {

            List<TotalCustomerTariffs> totalCustomerTariffs_s = new ArrayList<>();

            for (NamedExpression namedExpression : datasets) {
                List<Double> data_list = new ArrayList<>();
                data_list.add(namedExpression.getValue());
                TotalCustomerTariffs totalCustomerTariffs = new TotalCustomerTariffs();
                totalCustomerTariffs.setData(data_list);
                totalCustomerTariffs.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                totalCustomerTariffs_s.add(totalCustomerTariffs);
            }

            neDiagrams.setTotalCustomerTariffs(totalCustomerTariffs_s);
            data.removeAll(datasets);
        }

            if(label.equals("Total Inbound Cost")) {

                List<TotalInboundCost> totalInboundCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalInboundCost totalInboundCost = new TotalInboundCost();
                    totalInboundCost.setData(data_list);
                    totalInboundCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalInboundCosts.add(totalInboundCost);
                }

                neDiagrams.setTotalInboundCost(totalInboundCosts);
                data.removeAll(datasets);
            }


            if(label.equals("Total Outbound Cost")) {

                List<TotalOutboundCost> totalOutboundCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalOutboundCost totalOutboundCost = new TotalOutboundCost();
                    totalOutboundCost.setData(data_list);
                    totalOutboundCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalOutboundCosts.add(totalOutboundCost);
                }

                neDiagrams.setTotalOutboundCost(totalOutboundCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Transportation Cost")) {

                List<TotalTransportationCost> totalTransportationCosts = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalTransportationCost totalTransportationCost = new TotalTransportationCost();
                    totalTransportationCost.setData(data_list);
                    totalTransportationCost.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalTransportationCosts.add(totalTransportationCost);
                }

                neDiagrams.setTotalTransportationCost(totalTransportationCosts);
                data.removeAll(datasets);
            }

            if(label.equals("Total Penalties")) {

                List<TotalPenalties> totalPenalties_s = new ArrayList<>();

                for (NamedExpression namedExpression : datasets) {
                    List<Double> data_list = new ArrayList<>();
                    data_list.add(namedExpression.getValue());
                    TotalPenalties totalPenalties = new TotalPenalties();
                    totalPenalties.setData(data_list);
                    totalPenalties.setLabel(namedExpression.getExpression_name() + " iteration: " + namedExpression.getIteration());
                    totalPenalties_s.add(totalPenalties);
                }

                neDiagrams.setTotalPenalties(totalPenalties_s);
                data.removeAll(datasets);
            }
    }
        return neDiagrams;
    }




}
