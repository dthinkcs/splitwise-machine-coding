import java.util.*;
import java.util.Map.Entry;

public class ExpenseManager {
	List<Expense> expenses;
	// userMap: userId to User
	Map<String, User> userMap;
	Map<User, Map<User, Double>> balanceSheet;

	public ExpenseManager() {
		this.expenses = new ArrayList<Expense>();
		this.userMap = new HashMap<String, User>();
		this.balanceSheet = new HashMap<User, Map<User, Double>>();
	}

	public void addUder(User user) {
		userMap.put(user.getId(), user);
		balanceSheet.put(user, new HashMap<User, Double>());
	}
	
	public void addExpense (ExpenseType type, double amount, User paidBy, List<Split> splits) {
		//TODO add expense, add to expenses list, add to balance sheet
		Expense newExpense = SplitwiseService.createExpense(type, amount, paidBy, splits);
		expenses.add(newExpense);
		
		for (Split split : newExpense.getSplits()) {
			User paidTo = split.getUser();
			Map<User, Double> balances = balanceSheet.get(paidBy);
			if (!balances.containsKey(paidTo)) {
				balances.put(paidTo, 0.0);
			}
			balances.put(paidTo, balances.get(paidTo)+split.getAmount());
			balances = balanceSheet.get(paidTo);
			
			if (!balances.containsKey(paidBy)) {
				balances.put(paidBy, 0.0);
			}
			balances.put(paidBy, balances.get(paidBy) - split.getAmount());
		}
	}
	
	public void showBalanceForUser (String userId) {
		//TODO
		User thisUser = userMap.get(userId);
		if (thisUser!= null) {
			for (Map.Entry<User, Double> userBalance : balanceSheet.get(thisUser).entrySet()) {
				printBalances(userBalance);
			}
		}else {
			System.out.println("User doesn't exist");
		}
		
	}
	
	private void printBalances(Entry<User, Double> userBalance) {
		//TODO
	}

	public void showAllBalances () {
		//TODO
	}

}