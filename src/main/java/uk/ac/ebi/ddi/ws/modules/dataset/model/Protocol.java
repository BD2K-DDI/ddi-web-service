package uk.ac.ebi.ddi.ws.modules.dataset.model;

/**
 * The class protocol defines how to describe a general protocol, in Proteomics we have only
 * Data Protocol, Sample Protocol, etc. But For example in Metabolomics different protocols can be added in order
 * to describe the experiment.
 *
 * @author Yasset Perez-Riverol (ypriverol@gmail.com)
 * @date 01/06/2015
 */

public class Protocol {

    /**
     * Name of the protocol
     */
    String name = null;

    /**
     * Description of the protocol
     */
    String description = null;

    public Protocol(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
