package app.DAOs;

import app.App;
import app.DTOs.TitularDTO;
import app.Entidades.Titular;

public class TitularDAO {
    
    Titular titular;
    
    public Titular getTitular(TitularDTO t){  
        return App.entityManager.find(Titular.class, t);
    }

    public void CrearTitular(TitularDTO admin){

        if(getTitular(admin) == null){
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
            App.entityManager.getTransaction().commit();
        }
        else
            System.out.println("ya existe un titular");
        
    }

    public void ModificarTitular(TitularDTO admin){

        if(getTitular(admin) == null){
            System.out.println("no existe un titular");
        }
        else{
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
            App.entityManager.getTransaction().commit();
        }
    }

    public void EliminarTitular(TitularDTO admin){

        // deberia marcarlo como eliminado, no eliminarlo como tal

        if(getTitular(admin) == null){
            System.out.println("no se puede");
        }
        else{
            App.entityManager.getTransaction().begin();
            App.entityManager.merge(admin);
            App.entityManager.getTransaction().commit();
        }
    }
}
