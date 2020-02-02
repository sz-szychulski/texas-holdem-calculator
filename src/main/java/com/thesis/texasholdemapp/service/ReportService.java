package com.thesis.texasholdemapp.service;

import com.thesis.texasholdemapp.database.ReportConnection;
import com.thesis.texasholdemapp.model.Player;
import com.thesis.texasholdemapp.model.Simulation;
import com.thesis.texasholdemapp.repository.PlayerRepository;
import com.thesis.texasholdemapp.repository.SimulationRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReportService {

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private SimulationRepository simulationRepository;

    private ReportConnection reportConnection = new ReportConnection();
    private Connection databaseConnection = reportConnection.getConnection();

    public void exportReport(Long id) throws JRException {
        String simulationPath = System.getProperty("user.dir") + File.separator + "raport" + File.separator + "simulation_raport.jrxml";
        String playersPath = System.getProperty("user.dir") + File.separator + "raport" + File.separator + "players_raport.jrxml";

        JasperDesign simulationJasperDesign = JRXmlLoader.load(simulationPath);
        JasperDesign playerJasperDesign = JRXmlLoader.load(playersPath);

        JRDesignQuery selectSimulationQuery = new JRDesignQuery();
        selectSimulationQuery.setText("SELECT * FROM simulation WHERE simulation_id = " + id);
        simulationJasperDesign.setQuery(selectSimulationQuery);

        JRDesignQuery selectPlayerQuery = new JRDesignQuery();
        selectPlayerQuery.setText("SELECT * FROM player WHERE simulation_id = " + id);
        playerJasperDesign.setQuery(selectPlayerQuery);


        JasperReport simulationJasperReport = JasperCompileManager.compileReport(simulationJasperDesign);
        JasperReport playerJasperReport = JasperCompileManager.compileReport(playerJasperDesign);

        JasperPrint jasperPrintSimulation = JasperFillManager.fillReport(simulationJasperReport, null, databaseConnection);
        JasperPrint jasperPrintPlayers = JasperFillManager.fillReport(playerJasperReport, null, databaseConnection);

        List pages = jasperPrintPlayers.getPages();
        for(Object page : pages) {
            JRPrintPage object = (JRPrintPage)page;
            jasperPrintSimulation.addPage(object);
        }

        JasperExportManager.exportReportToPdfFile(jasperPrintSimulation, buildOutputPath(simulationRepository.getOne(id)));
    }

    private String buildOutputPath(Simulation simulation) {
        return System.getProperty("user.dir") + File.separator + "raport" + File.separator + "output" + File.separator +
                simulation.getSimulationName() + ".pdf";
    }

}
