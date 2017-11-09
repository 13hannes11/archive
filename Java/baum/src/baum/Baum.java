/*
 * binäre Bäume
 *  und wie man damit umgeht
 * 
 * Thema: Komprimiere einen Text
 * Beispiel: iQ1Bäume_131203
 * "Bäume sind auch in der Informatik ein wichtiges Thema. Viele Daten werden zunächst in Bäumen abgespeichert. Sie werden vor allem zur Suche und Sortierung verwendet." 
 * 
 */
package baum;

/**
 *
 * @author frank.baethge
 */
public class Baum {
  private static Ast wurzel;
  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    wurzel = new Ast(//164
            new Ast(//79
                new Ast(//56
                    new Ast(//28
                        new Blatt('n', 13),
                        new Ast(//15
                            new Blatt('t', 7),
                            new Blatt('u', 8)
                        )
                    ),
                    new Ast(//28
                        new Ast(//16
                            new Ast(//8
                                new Blatt('s', 4), 
                                new Blatt('w', 4)),
                            new Ast(//8
                                new Blatt('m', 5),
                                new Blatt('.', 3)
                            )
                        ),
                        new Ast(//12
                            new Ast(//6
                                new Blatt('g', 3),
                                new Blatt('S', 3)
                            ),
                            new Ast(//6
                                new Blatt('o', 3),
                                new Blatt('l', 3)
                            )
                        )
                    )
                ),
                new Ast(//23
                    new Blatt('i', 11),
                    new Ast(//12
                        new Blatt('d', 6),
                        new Blatt('a', 6)
                    )
                )
            ),
            new Ast(//85
                new Blatt(' ', 24),
                new Ast(//61
                    new Ast(//32
                        new Ast(//9
                            new Ast(//5
                                new Blatt('B', 2),
                                new Blatt('ä', 3)
                            ),
                            new Ast(//4
                                new Blatt('z', 2),
                                new Blatt('v', 2)
                            )
                        ),
                        new Blatt('e', 23)
                    ),
                    new Ast(//29
                        new Ast(//8
                            new Ast(//4
                                new Ast(//2
                                    new Blatt('f', 1),
                                    new Blatt('b', 1)
                                ),
                                new Ast(//2
                                    new Blatt('k', 1),
                                    new Blatt('p', 1)
                                )
                            ),
                            new Ast(//4
                                new Ast(//2
                                    new Blatt('V', 1),
                                    new Blatt('T', 1)
                                ),
                                new Ast(//2
                                    new Blatt('I', 1),
                                    new Blatt('D', 1)
                                )
                            )
                        ),
                        new Ast(//21
                            new Blatt('r', 10),
                            new Ast(//11
                                new Blatt('h', 6),
                                new Blatt('c', 5)
                            )
                        )
                    )
                )
            )
    );
    //System.out.println("Bitlänge des komprimierten Textes: " + wurzel.berechneBitlänge(0));
    System.out.println("Wurzel: " + wurzel.ausgeben(""));
    
    // rotiere Wurzel links
    Ast alteWurzel = wurzel;
    wurzel = wurzel.getRechts();
    alteWurzel.setRechts(wurzel.getLinks());
    wurzel.setLinks(alteWurzel);
    System.out.println("Bitlänge des komprimierten Textes nach der Rotation: " + wurzel.berechneBitlänge(0));
    
  }
}
