/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audit;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe para registro de auditoria resposável 
 * em auditar qualquer tipo de classe com métodos anotados.
 * 
 * @author Klaus Boeing
 * @author Heric
 * 
 * @since 1.0
 * @see Audit
 */
public class RegistroAuditoria {

    /**
     * Método responsável em registra o campos auditáveis em um objeto de uma classe.
     * @param object objeto no qual os campos serão auditados
     */
    public void registra(Object object) {
        for (Method method : object.getClass().getMethods()) {
            if (method.isAnnotationPresent(Audit.class)) {
                Audit audit = method.getAnnotation(Audit.class);
                try {
                    Object value = method.invoke(object);

                    String descricao = audit.value();
                    if(descricao.isEmpty()){
                        descricao = method.getName();
                    }
                    
                    System.out.println(String.format("Registro auditado: %s valor: %s data: %s", descricao, value, new Date()));
                } catch (SecurityException securityException) {
                    securityException.printStackTrace();
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(RegistroAuditoria.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(RegistroAuditoria.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(RegistroAuditoria.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     * Método responsável em listar os métods auditaveis de um objeto de uma classe.
     * @param object 
     * @return a relação de métodos auditaveis de uma classe
     * @throws IllegalArgumentException quando um objeto não possuir nenhum método anotado.
     * @deprecated Este método foi deprecated por que é possível obter o métodos auditaveis manualmente.
     */
    public Method[] getMetodosAuditaveis(Object object) throws IllegalArgumentException{
        return null;
    }
    
}
