package uk.ac.ebi.ddi.ws.modules.stats.controller;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uk.ac.ebi.ddi.ebe.ws.dao.model.europmc.Citation;
import uk.ac.ebi.ddi.service.db.model.dataset.MostAccessedDatasets;
import uk.ac.ebi.ddi.service.db.model.similarity.Citations;
import uk.ac.ebi.ddi.service.db.model.similarity.EBISearchPubmedCount;
import uk.ac.ebi.ddi.service.db.model.similarity.ReanalysisData;
import uk.ac.ebi.ddi.service.db.service.dataset.IMostAccessedDatasetService;
import uk.ac.ebi.ddi.service.db.service.similarity.CitationService;
import uk.ac.ebi.ddi.service.db.service.similarity.EBIPubmedSearchService;
import uk.ac.ebi.ddi.service.db.service.similarity.ReanalysisDataService;
import uk.ac.ebi.ddi.ws.modules.dataset.controller.DatasetController;
import uk.ac.ebi.ddi.ws.modules.dataset.model.DataSetResult;
import uk.ac.ebi.ddi.ws.modules.dataset.model.DatasetSummary;
import uk.ac.ebi.ddi.ws.modules.dataset.model.OmicsDataset;
import uk.ac.ebi.ddi.ws.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by gaur on 29/08/17.
 */
@Api(value = "scores", description = "Retrieve the information about the dataset scores", position = 0)
@Controller
@RequestMapping(value = "/scores")
public class ScoresController {

    private static final Logger logger = LoggerFactory.getLogger(ScoresController.class);

    @Autowired
    private CitationService citationService;

    @Autowired
    private EBIPubmedSearchService ebiPubmedSearchService;

    @Autowired
    private ReanalysisDataService reanalysisDataService;

    @Autowired
    private IMostAccessedDatasetService mostAccessedDatasetService;

    @ApiOperation(value = "Retrieve an Specific Dataset Citation Count", position = 1, notes = "Retrieve an specific dataset citation count")
    @RequestMapping(value = "/citation/{source}/{acc}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    Citations getDatasetCitations(
            @ApiParam(value = "Accession of the Dataset in the resource, e.g : E-TIGR-123")
            @PathVariable(value = "acc") String acc,
            @ApiParam(value = "source, e.g: arrayexpress_repository")
            @PathVariable(value = "source") String source){
        String database = Constants.Database.retriveAnchorName(source);
        return citationService.read(acc,database);
    }

    @ApiOperation(value = "Retrieve an Specific Dataset search Count", position = 1, notes = "Retrieve an specific dataset search count")
    @RequestMapping(value = "/search/{acc}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    EBISearchPubmedCount getDataSearchCount(
            @ApiParam(value = "Accession of the Dataset in the resource, e.g : E-GEOD-24657")
            @PathVariable(value = "acc") String acc){

        return ebiPubmedSearchService.getSearchCount(acc);
    }

    @ApiOperation(value = "Retrieve an Specific Dataset reanalysis Count", position = 1, notes = "Retrieve an specific dataset reanalysis count")
    @RequestMapping(value = "/renalysis/{source}/{acc}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    ReanalysisData getDataReanalysisCount(
            @ApiParam(value = "Accession of the Dataset in the resource, e.g : E-GEOD-2198")
            @PathVariable(value = "acc") String acc,
            @ApiParam(value = "source, e.g: atlas-experiments")
            @PathVariable(value = "source") String source){
        String database = Constants.Database.retriveAnchorName(source);
        return reanalysisDataService.getReanalysisCount(acc,database);
    }


    @ApiOperation(value = "views of specific dataset", position = 1, notes = "Retrieve an specific dataset view count")
    @RequestMapping(value = "/views/{source}/{acc}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK) // 200
    public @ResponseBody
    int getDatasetViews(
            @ApiParam(value = "Accession of the Dataset in the resource, e.g : 10090109")
            @PathVariable(value = "acc") String acc,
            @ApiParam(value = "source, e.g: paxdb")
            @PathVariable(value = "source") String source)
    {
        String database = Constants.Database.retriveAnchorName(source);
        MostAccessedDatasets datasets = mostAccessedDatasetService.getDatasetView(acc,database);
        return datasets.getTotal();
    }

}
