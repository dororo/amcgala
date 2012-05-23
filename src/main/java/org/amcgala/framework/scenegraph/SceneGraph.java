/* 
 * Copyright 2011 Cologne University of Applied Sciences Licensed under the
 * Educational Community License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may
 * obtain a copy of the License at
 *
 * http://www.osedu.org/licenses/ECL-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an "AS IS"
 * BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */
package org.amcgala.framework.scenegraph;

import org.amcgala.framework.scenegraph.transform.Transformation;
import org.amcgala.framework.scenegraph.visitor.Visitor;
import org.amcgala.framework.shape.Shape;

import java.util.Collection;
import java.util.HashSet;

/**
 * Szenengraph des Frameworks.
 */
public class SceneGraph {

    private Node root = new Node("root");
    private int nodeCount = 1;

    /**
     * Fügt dem Szenengraph einen Knoten hinzu.
     * Dieser wird direkt an den Wurzelknoten des Graphen gehängt.
     *
     * @param node der neue Knoten
     */
    public void addNode(Node node) {
        if (node != null) {
            root.addChild(node);
            nodeCount++;
        } else {
            throw new IllegalArgumentException("Node darf nicht null sein!");
        }
    }

    /**
     * Fügt dem Knoten mit der übergebenen Bezeichnung einen neuen Kindsknoten hinzu.
     *
     * @param node  der Kindsknoten, der hinzugefügt werden soll
     * @param label der Name des Knoten, an den der Kindsknoten gehängt werden soll
     */
    public void addNode(Node node, String label) {
        if (node == null || label == null) {
            throw new IllegalArgumentException("node oder label dürfen nicht null sein!");
        } else {
            getNode(label).addChild(node);
            nodeCount++;
        }
    }

    /**
     * Gibt den Knoten zurück.
     * @param label der Name des Knoten
     * @return der Knoten, wenn er im Szenengraph ist, sonst null
     */
    public Node getNode(String label) {
        Collection<Node> nodes = getAllNodes();
        for(Node n : nodes){
            if(n.getLabel().equalsIgnoreCase(label)){
                return n;
            }
        }
        return null;
    }

    private Collection<Node> getAllNodes() {
        Collection<Node> nodes = new HashSet<Node>();
        fillWithNodes(nodes, root);
        return nodes;
    }

    /**
     * Entfernt den Knoten aus dem Szenengraph.
     *
     * @param node der Knoten,der entfernt werden soll
     */
    public void removeNode(Node node) {
        if (node != null) {
            root.removeNode(node.getLabel());
            nodeCount--;
        } else {
            throw new IllegalArgumentException("node darf nicht null sein!");
        }
    }

    /**
     * Entfernt den Knoten mit der übergebenen Bezeichnung aus dem Szenengraph.
     *
     * @param label der Name des Knotens, der entfernt werden soll
     */
    public Node removeNode(String label) {
        if (label != null) {
            Node n = root.removeNode(label);
            if (n != null){
                nodeCount--;
                return n;
            }else{
                return null;
            }
        } else {
            throw new IllegalArgumentException("label darf nicht null sein!");
        }
    }


    private void fillWithNodes(Collection<Node> collection, Node node){
        if(node != null){
            collection.add(node);
            for(Node n : node.getChildren()){
                fillWithNodes(collection, n);
            }
        }
    }

    /**
     * Fügt einem bestimmten Knoten innerhalb des Szenengraphs ein neues Shapeobjekt hinzu.
     *
     * @param label der Name des Knotens
     * @param shape das Shapeobjekt
     */
    public void addShape(String label, Shape shape) {
        root.addShape(label, shape);
    }

    /**
     * Fügt dem Wurzelknoten ein neues Shapeobjekt hinzu.
     *
     * @param shape das Shapeobjekt
     */
    public void addShape(Shape shape) {
        addShape("root", shape);
    }

    /**
     * Teil des Visitor-Patterns. Eintrittspunkt der Visitors
     *
     * @param visitor der Visitor
     */
    public void accept(Visitor visitor) {
        root.accept(visitor);
    }

    /**
     * Fügt dem Wurzelknoten eine neue Transformation hinzu.
     *
     * @param transformation die neue Transformation
     */
    public void addTransformation(Transformation transformation) {
        root.setTransformation(transformation);
    }

    /**
     * Fügt einem bestimmten Knoten innerhalb des Szenengraphs eine neue Transformation hinzu.
     *
     * @param transformation die neue Transformation
     * @param label          die Bezeichnung des gesuchten Knotens
     */
    public void addTransformation(Transformation transformation, String label) {
        getNode(label).setTransformation(transformation);
    }


    /**
     * Gibt die Anzahl der Knoten im Szenengraph zurück.
     * @return die Anzahl der Knoten
     */
    public int getNodeCount() {
        return nodeCount;
    }


}
