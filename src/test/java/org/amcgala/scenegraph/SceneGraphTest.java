package org.amcgala.scenegraph;

import org.amcgala.framework.scenegraph.Node;
import org.amcgala.framework.scenegraph.SceneGraph;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

/**
 * Test der SceneGraph Klasse.
 *
 * @author Robert Giacinto
 */
public class SceneGraphTest {
    private SceneGraph sceneGraph;

    @Before
    public void setup(){
        sceneGraph = new SceneGraph();
    }


    @Test
    public void getRootNodeTest(){
        Node node = sceneGraph.getNode("root");
        assertEquals("Methode hat nicht den Rootknoten zurückgegeben!", node.getLabel(), "root");
    }

    @Test
    public void getNodeTest(){
        Node a = new Node("a");
        Node b = new Node("b");
        Node c = new Node("c");
        Node d = new Node("d");
        Node e = new Node("e");

        a.addChild(b);
        a.addChild(c);

        c.addChild(d);
        d.addChild(e);

        sceneGraph.addNode(a);

        Node result = sceneGraph.getNode("e");
        assertSame(e,result);
    }


}
