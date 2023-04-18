package view.worldpanel;

import javax.swing.*;

import controller.Controller;
import model.actors.person.PersonAbstract.Role;
import model.exceptions.CustomExceptions.ActionNotAvailableException;
import model.place.Place;
import model.place.Places;
import model.place.land.LandAbstract;
import view.View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class WorldPanelView {
    private JPanel worldPanel;
    private Controller controller;
    private View view;
    
    public WorldPanelView(Controller controller, View view) throws ActionNotAvailableException {
        this.controller = controller;
        this.view = view;

        this.worldPanel = createWorldPanel();
    }

    @SuppressWarnings("unchecked")
    public JPanel createWorldPanel() throws ActionNotAvailableException{
        /*
        * This method creates the world panel, which contains the land and barn panels
        */
        // create the panel
        worldPanel = new JPanel(new GridLayout(1, 2));
        worldPanel.setPreferredSize(new Dimension(800, 500));

        // create the land and barn panels
        JPanel barn = new JPanel(new GridBagLayout());
        JPanel landsPanel = new JPanel(new GridLayout(3, 3,40,40));

        int landNumber = 1;

        // enable the possibility to change the role
        this.view.getRolePanelView().getRoleMenu().setEnabled(true);

        // add the land buttons
        for (LandAbstract land : this.controller.getLands()) {
            JButton button = new JButton(landNumber+". "+land.getType().toString());
            button.addActionListener(changePlaceListener(view.getLandView(),"createInsideLand",land,false,false));
            landsPanel.add(button);
            // disable land button if the role is not the farmer
            button.setEnabled((controller.getSelectedPerson().getRole() == Role.FARMER) ? true : false);
            landNumber++;
        }
        // add the barn button
        JButton barnButton = new JButton((this.controller.getBarn()).getType().toString());
        
        barnButton.addActionListener(changePlaceListener(view.getBarnView(),"createBarnPlace", (Place)(this.controller.getBarn()),true,false));

        barnButton.setPreferredSize(new Dimension(200,200));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 1;
        gbc.weightx = 0;
        gbc.weighty = 0;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(0, 0, 0, 0);
        barn.add(barnButton, gbc);
    
        // add the next day button
        JButton nextDay = new JButton("Next day");
        nextDay.addActionListener(new ActionListener() {
        @Override
            public void actionPerformed(ActionEvent e) {
                controller.updateModel();
                try {
                controller.setOldPlace(controller.getSelectedPerson().getPlace());
                view.updateActualPanel(worldPanel, createWorldPanel());
                } catch (ActionNotAvailableException e1) {
                    view.exceptionPopup(e1.getCause().getMessage());
                }
            }
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.anchor = GridBagConstraints.SOUTHEAST;
        c.insets = new Insets(10, 10, 10, 0);
        c.gridwidth = 1;
        barn.add(nextDay, c);    

        worldPanel.add(landsPanel);
        worldPanel.add(barn);

        // update the labels
        view.getRolePanelView().updateLabels();
        return worldPanel;
    }

    private <T> Method getMethodByName(String methodName, Class<T> c) {
        /*
        * Method to get a method by its name
        */
        Method[] methods = c.getDeclaredMethods();
        for (Method m : methods) {
            if (m.getName().equals(methodName)) {
                return m;
            }
        }
        return null;
    }

    public ActionListener changePlaceListener(Object classInstance,String methodName, Place place, Boolean reqArgs, Boolean exit) {
        /*
        * Method to create an action listener for the enter and leave buttons
        */

        return new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if (exit) {
                        controller.setOldPlace(controller.leaveOldPlace());
                    } else {
                        controller.setOldPlace(controller.enterNewPlace(place));
                    }
                    if (view.getRolePanelView().getShowInventoryButton().isSelected())
                    view.getRolePanelView().getShowInventoryButton().doClick();
                    view.updateActualPanel(worldPanel, reqArgs ? (JPanel) (getMethodByName(methodName,classInstance.getClass()).invoke(classInstance, place))
                                                               : (JPanel) (getMethodByName(methodName,classInstance.getClass()).invoke(classInstance)));
                } catch (IllegalAccessException |
                        IllegalArgumentException | 
                        InvocationTargetException | 
                        ActionNotAvailableException e1) {
                    view.exceptionPopup(e1.getCause().getMessage());
                }
            }
        };
    }

    public JPanel getWorldPanel() {
        return worldPanel;
    }
}
