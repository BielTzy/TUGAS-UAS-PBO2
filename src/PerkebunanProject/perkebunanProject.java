/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package PerkebunanProject;
import perkebunan.*;
import form.*;
/**
 *
 * @author ASUS
 */
public class perkebunanProject {
    
    public static void main(String[] args) {
        // TODO code application logic here
        
        frameAdmin formAdmin = new frameAdmin();
        formAdmin.setVisible(true);
        
        framePerusahaan formPerusahaan = new framePerusahaan();
        formPerusahaan.setVisible(true);
        
        framePengunjung formPengunjung = new framePengunjung();
        formPengunjung.setVisible(true);
        
        frameKomoditi formKomoditi = new frameKomoditi();
        formKomoditi.setVisible(true);
        
        frameInfo formInfo = new frameInfo();
        formInfo.setVisible(true);
        
    }
    
}
