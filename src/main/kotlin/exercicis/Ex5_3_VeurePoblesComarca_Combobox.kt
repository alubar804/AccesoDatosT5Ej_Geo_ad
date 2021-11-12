@file:Suppress("LanguageDetectionInspection")

package exercicis



import java.awt.EventQueue
import java.awt.BorderLayout
import java.awt.FlowLayout
import java.awt.Color
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.JScrollPane
import javax.swing.JComboBox
import javax.swing.DefaultListModel
import javax.swing.JList

import classes.PoblacioEntity
import classes.ComarcaEntity
import classes.InstitutEntity
import org.hibernate.MappingException
import org.hibernate.cfg.Configuration
import java.util.logging.Level
import java.util.logging.LogManager


class FinestraComboBox : JFrame() {
    val etiqueta = JLabel("Comarca:")
    val etIni = JLabel("Introdueix la comarca:")
    val com = JComboBox<String>()
    val listModel = DefaultListModel<String>()
    val area = JList(listModel)
    val peu = JTextField()

    val sessio = Configuration().configure().buildSessionFactory().openSession()

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("HIBERNATE: Visualitzar Comarques i Pobles amb ComboBox")
        setBounds(100, 100, 550, 400)
        setLayout(BorderLayout())

        val panell1 = JPanel(FlowLayout())
        panell1.add(etIni)
        panell1.add(com)
        getContentPane().add(panell1, BorderLayout.NORTH)

        val panell2 = JPanel(BorderLayout())
        panell2.add(etiqueta, BorderLayout.NORTH)
        area.setForeground(Color.blue)
        val scroll = JScrollPane(area)
        panell2.add(scroll, BorderLayout.CENTER)
        getContentPane().add(panell2, BorderLayout.CENTER)
        getContentPane().add(peu, BorderLayout.SOUTH)

        agafarComarques()

        com.addActionListener() { visualitzaCom(com.getSelectedItem().toString()) }

        area.addListSelectionListener() {
            if (!area.isSelectionEmpty())
                visualitzaInstituts(area.getSelectedValue())
            else
                peu.setText("")
        }
    }

    fun agafarComarques() {
        // Instruccions per a posar en el ComboBox el nom de totes les comarques, millor si és per ordre alfabètic
        var comacaLista = mutableListOf<String>()
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
        val q = sessio.createQuery ("from ComarcaEntity")

        for (c in q.list()) {
            c as ComarcaEntity
            comacaLista.add(c.nomC )
        }
        comacaLista.sortBy { it }
        for (i in comacaLista)
            com.addItem(i)
    }

    fun visualitzaCom(comarca: String) {
        // Instruccions per a llegir la comarca que arriba com a paràmetre (s'ha de deixar en un objecte Comarca).
        // S'ha de cuidar que si no exiteix la comarca, en el JList es pose que no existeix.
        // La manera d'anar introduint informació en el JList és a través del DefaultListModel:
        // listModel.addElement("Linia que es vol introduir ")
        // Per a esborrar els element del JList: listModel.clear()
        // Es pot fer carregant un objecte, o per mig de consulta, però en aquest cas podem tenir problemes amb '
        // Una manera de solucionar el problema de la cometa simple és utilitzar comarca.replaceAll("'","''").
        // Una altra és utilitzar paràmetres
        listModel.clear()
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
        comarca.replace("'","''")
        try {
            val d = sessio.createQuery ("from ComarcaEntity where nomC='$comarca'").uniqueResult() as ComarcaEntity
            val pueblos = mutableListOf<String>()
            for (p in d.poblacions)
                pueblos.add(p.nom)
            pueblos.sortBy { it }
            for (i in pueblos)
                listModel.addElement(i)

        }catch (e:MappingException){
            listModel.addElement("La comarca no existeix")
        }

    }

    fun visualitzaInstituts(poble: String) {
        // Instruccions per a mostrar el número d'Instituts del poble seleccionat
        // La millor manera és per mig d'una consulta. Podem tenir problemes amb la cometa simple
        // Una manera de solucionar el problema de la cometa simple és utilitzar poble.replaceAll("'","''").
        // Una altra és utilitzar paràmetres
        poble.replace("'","''")
        LogManager.getLogManager().getLogger("").setLevel(Level.SEVERE)
        try {
            val d = sessio.createQuery ("from PoblacioEntity where nom='$poble'").uniqueResult() as PoblacioEntity

            val numero = d.institut.size
            peu.setText("$numero")

        }catch (e:MappingException){
            listModel.addElement("La comarca no existeix")
        }
    }

}

fun main() {
    EventQueue.invokeLater {
        FinestraComboBox().isVisible = true
    }
}

