package co.edu.unicauca.commandrestaurant.domain;

import co.edu.unicauca.commandrestaurant.access.IFoodRepository;
import co.edu.unicauca.commandrestaurant.access.RepositoryFactory;
import co.edu.unicauca.commandrestaurant.domain.service.FoodService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author kevith Bastidas
 */
public class DeleteCommand extends Command{

    /**
     * Comida a crear
     */
    private Food food;
    /**
     * Instancia al receptor
     */
    private FoodService service;
    
    /**
     * Constructor parametrizado
     * @param food comida a eliminar
     */
    public DeleteCommand(Food food){
        this.food = food;
        IFoodRepository repo = RepositoryFactory.getInstance().getRepository("default");;
        service = new FoodService(repo);
        this.hasUndo = true;
    }
    
    @Override
    public void execute() {
        Logger logger= LoggerFactory.getLogger(DeleteCommand.class); 
        logger.info("Comando de eliminacion se ha ejecutado. Se elimino la comida " + food);  
        service.delete(food.getId());
    }

    @Override
    public void undo() {
        Logger logger= LoggerFactory.getLogger(DeleteCommand.class); 
        logger.info("Comando de eliminacion se ha deshecho. Se agrego la comida " + food);  
        service.create(food);
    }
    
    
    
}
