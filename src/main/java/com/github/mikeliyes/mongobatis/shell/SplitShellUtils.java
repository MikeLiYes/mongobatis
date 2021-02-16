package com.github.mikeliyes.mongobatis.shell;

import java.util.Arrays;

import com.github.mikeliyes.mongobatis.exception.MessageException;
import com.github.mikeliyes.mongobatis.model.ShellMethod;
import com.github.mikeliyes.mongobatis.utils.StringUtils;

public class SplitShellUtils {
	
	public static void splitMethodShell(ShellMethod method){
		
		if (method == null || StringUtils.isBlank(method.getShell())) {
			throw new MessageException("method shell is null");
		}
		
		String shell = method.getShell();
		
		spliteAggregateShell(method, shell);

	}

	private static void spliteAggregateShell(ShellMethod method, String shell) {
		//aggregate method
		if (ShellMethod.METHOD_TYPE_AGGREGATE.equalsIgnoreCase(method.getMethodType())) {
			int start = shell.indexOf(ShellMethod.SPLITE_START);
			
			int end = shell.indexOf(ShellMethod.SPLITE_END);
			
			if (start == -1 || end == -1) {
				throw new MessageException("aggregate method should has ([{}])");
			}
			
			if (start >= 0 && end >= 0) {
				String pipeShell = shell.substring(start + ShellMethod.SPLITE_START.length(), end);
				
				String[] pipes = pipeShell.split(ShellMethod.PIPE_SPLITE);

				for (int i = 0;i < pipes.length;i++) {
					
					pipes[i] = "{" + pipes[i] + "}";
					
					int pre = pipes[i].indexOf(ShellMethod.PLACE_HOLDER_PRE);
					int suf = pipes[i].indexOf(ShellMethod.PLACE_HOLDER_SUF);
					
					if (pre >= 0 && suf >= 0) {
						method.setSplitePlaceLoc(i);
					}
				}
				
				method.setSplitShell(Arrays.asList(pipes));
				
			}
		}
	}
	
}
