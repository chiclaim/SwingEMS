package database.practise.presenter.employee;

import database.practise.bean.Employee;
import database.practise.dao.EmployeeDaoImpl;
import database.practise.dao.IDao;

import javax.swing.*;
import java.util.List;

public class EmployeePresenter implements IEmployeeContract.Presenter {

    private IEmployeeContract.View view;

    private IDao<Employee> employeeIDao;

    public EmployeePresenter(IEmployeeContract.View view) {
        this.view = view;
        employeeIDao = new EmployeeDaoImpl();
    }

    @Override
    public void getList(Employee employee) {
        new SwingWorker<List<Employee>, Void>() {
            @Override
            protected List<Employee> doInBackground() throws Exception {
                return employeeIDao.query(employee);
            }

            @Override
            protected void done() {
                super.done();
                if (view == null) return;
                try {
                    List<Employee> list = get();
                    view.getListSuccess(list);
                } catch (Exception e) {
                    e.printStackTrace();
                    view.showMessageDialog(e);
                }

            }

        }.execute();

    }

    @Override
    public void update(Employee employee) {
        System.out.println("update:" + employee);
        new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return employeeIDao.update(employee);
            }

            @Override
            protected void done() {
                super.done();
                try {
                    get();
                } catch (Exception e) {
                    e.printStackTrace();
                    view.showMessageDialog(e);
                }
            }
        }.execute();
    }

    @Override
    public void delete(Employee employee) {
        new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return employeeIDao.remove(employee.getId());

            }

            @Override
            protected void done() {
                super.done();
                try {
                    if (get() > 0) view.deleteSuccess(employee);
                } catch (Exception e) {
                    e.printStackTrace();
                    view.showMessageDialog(e);
                }
            }
        }.execute();

    }

    @Override
    public void add(Employee employee) {
        new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                return employeeIDao.add(employee);
            }

            @Override
            protected void done() {
                super.done();
                try {
                    if (get() > 0) view.addSuccess();
                } catch (Exception e) {
                    e.printStackTrace();
                    view.showMessageDialog(e);
                }
            }
        }.execute();

    }
}
