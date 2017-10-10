package br.com.insidegroup.insidecomerce.entidades;

/**
 * Created by Lucas on 09/10/2017.
 */

public class DescricaoStatuTicket {


    public static String descricaoStatusTicket(int status){
        String descricaoStatus = "";
        switch (status) {
            case 1:
                descricaoStatus = "Em Aberto";
                break;
            case 2:
                descricaoStatus = "Em Andamento";
                break;
            case 3:
                descricaoStatus = "Cliente cancelou Vistoria";
                break;
            case 4:
                descricaoStatus = "Cliente Recusou Contrato";
                break;
            case 5:
                descricaoStatus = "Cliente Fechou Contrato";
                break;
            case 6:
                descricaoStatus = "Vistoria Reagendada";
                break;
            case 7:
                descricaoStatus = "Vendedor Recusou";
                break;
        }

        return descricaoStatus;
    }

}
