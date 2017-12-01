/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PrbAutomataCad;

/**
 *
 * @author Maestria
 */
public class Automata {

    private String edoact;
    private String transicion;
    private String edosig;
    private String elemescrito;
    private String dir;

    public Automata(String edoact, String transicion, String edosig) {
        this.edoact = edoact;
        this.transicion = transicion;
        this.edosig = edosig;
    }

    public Automata(String edoact, String transicion, String edosig, String eleido, String dir) {
        this.edoact = edoact;
        this.transicion = transicion;
        this.edosig = edosig;
        this.elemescrito = eleido;
        this.dir = dir;
    }

    /**
     * @return the edoact
     */
    public String getEdoact() {
        return edoact;
    }

    /**
     * @param edoact the edoact to set
     */
    public void setEdoact(String edoact) {
        this.edoact = edoact;
    }

    /**
     * @return the transicion
     */
    public String getTransicion() {
        return transicion;
    }

    /**
     * @param transicion the transicion to set
     */
    public void setTransicion(String transicion) {
        this.transicion = transicion;
    }

    /**
     * @return the edosig
     */
    public String getEdosig() {
        return edosig;
    }

    /**
     * @param edosig the edosig to set
     */
    public void setEdosig(String edosig) {
        this.edosig = edosig;
    }

    /**
     * @return the eleido
     */
    public String getEleido() {
        return elemescrito;
    }

    /**
     * @param eleido the eleido to set
     */
    public void setEleido(String eleido) {
        this.elemescrito = eleido;
    }

    /**
     * @return the dir
     */
    public String getDir() {
        return dir;
    }

    /**
     * @param dir the dir to set
     */
    public void setDir(String dir) {
        this.dir = dir;
    }

}
