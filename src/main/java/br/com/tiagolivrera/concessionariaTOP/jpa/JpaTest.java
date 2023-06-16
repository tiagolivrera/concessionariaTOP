package br.com.tiagolivrera.concessionariaTOP.jpa;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import br.com.tiagolivrera.concessionariaTOP.domain.Acessorio;
import br.com.tiagolivrera.concessionariaTOP.domain.Carro;
import br.com.tiagolivrera.concessionariaTOP.domain.Marca;

public class JpaTest {

	private EntityManager manager;

	public JpaTest(EntityManager manager) {
		this.manager = manager;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("persistenceUnit");
		EntityManager manager = factory.createEntityManager();
		JpaTest test = new JpaTest(manager);

		EntityTransaction tx = manager.getTransaction();
		tx.begin();
		try {
			test.createDB();
		} catch (Exception e) {
			e.printStackTrace();
		}
		tx.commit();

		test.listAcessorios();
		System.out.println("..");
		test.listCarros();
		System.out.println("..");
		test.listMarcas();

		System.out.println(".. done");
	}

	private void createDB() {
		Acessorio a1 = new Acessorio();
		Acessorio a2 = new Acessorio();

		Carro c1 = new Carro();
		Marca m1 = new Marca();

		a1.setCodigo("A1");
		a1.setNome("Câmbio");
		a1.setDescricao("Câmbio automático de 6 velocidades");
		a1.setValor(BigDecimal.valueOf(12000.00));
		a1.setCarro(c1);
		a1.setMarca(m1);

		a2.setCodigo("A2");
		a2.setNome("Roda");
		a2.setDescricao("Roda de liga leve");
		a2.setValor(BigDecimal.valueOf(1200.00));
		a2.setCarro(c1);
		a2.setMarca(m1);

		c1.setNome("Hilux");
		c1.setChassi("C1A1A2M1");
		c1.setCor("Vermelho");
		c1.setAcessorios(new ArrayList<Acessorio>(List.of(a1, a2)));
		c1.setMarca(m1);
		c1.setValor(BigDecimal.valueOf(85000.00));

		m1.setAcessorios(new ArrayList<Acessorio>(List.of(a1, a2)));
		m1.setCarros(new ArrayList<Carro>(List.of(c1)));
		m1.setNome("Toyota");

		if (manager.createQuery("Select a From Acessorio a", Acessorio.class).getResultList().isEmpty()) {
			manager.persist(a1);
			manager.persist(a2);
		}
		if (manager.createQuery("Select c From Carro c", Carro.class).getResultList().isEmpty()) {
			manager.persist(c1);
		}

		if (manager.createQuery("Select m From Marca m", Marca.class).getResultList().isEmpty()) {
			manager.persist(m1);
		}

	}

	private void listAcessorios() {
		List<Acessorio> resultList = manager.createQuery("Select a From Acessorio a", Acessorio.class).getResultList();
		System.out.println("Número de acessórios: " + resultList.size());
		for (Acessorio a : resultList) {
			System.out.println(a.toString());
		}
	}

	private void listCarros() {
		List<Carro> resultList = manager.createQuery("Select c From Carro c", Carro.class).getResultList();
		System.out.println("Número de carros: " + resultList.size());
		for (Carro c : resultList) {
			System.out.println(c.toString());
		}
	}

	private void listMarcas() {
		List<Marca> resultList = manager.createQuery("Select m From Marca m", Marca.class).getResultList();
		System.out.println("Número de marcas: " + resultList.size());
		for (Marca m : resultList) {
			System.out.println(m.toString());
		}
	}
}
