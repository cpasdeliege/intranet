package be.cpasdeliege.intranet.informatique.model.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.LogRecord;
import java.util.logging.XMLFormatter;

public class LogFormatterIntranet extends XMLFormatter {

	public String format(LogRecord record) {
		if (record.getClass().getName().equals("be.cpasdeliege.intranet.informatique.model.domain.LogRecordIntranet")) {
			return format((LogRecordIntranet) record);
		} else {
			return "";
		}
	}

	public String format(LogRecordIntranet record) {
		StringBuffer sb = new StringBuffer(500);
		sb.append("<record>\n");

		sb.append("  <date>");
		appendISO8601(sb, record.getMillis());
		sb.append("</date>\n");

		sb.append("  <millis>");
		sb.append(record.getMillis());
		sb.append("</millis>\n");

		sb.append("  <sequence>");
		sb.append(record.getSequenceNumber());
		sb.append("</sequence>\n");

		String name = record.getLoggerName();
		if (name != null) {
			sb.append("  <logger>");
			escape(sb, name);
			sb.append("</logger>\n");
		}

		sb.append("  <level>");
		escape(sb, record.getLevel().toString());
		sb.append("</level>\n");

		if (record.getSourceClassName() != null) {
			sb.append("  <class>");
			escape(sb, record.getSourceClassName());
			sb.append("</class>\n");
		}

		if (record.getSourceMethodName() != null) {
			sb.append("  <method>");
			escape(sb, record.getSourceMethodName());
			sb.append("</method>\n");
		}

		sb.append("  <thread>");
		sb.append(record.getThreadID());
		sb.append("</thread>\n");

		if (record.getMessage() != null) {
			// Format the message string and its accompanying parameters.
			String message = formatMessage(record);
			sb.append("  <message>");
			escape(sb, message);
			sb.append("</message>");
			sb.append("\n");
		}

		sb.append("  <utilisateur>");
		sb.append(record.getUtilisateur());
		sb.append("</utilisateur>\n");

		sb.append("</record>\n");
		return sb.toString();
	}

	public void escape(StringBuffer sb, String text) {
		if (text == null) {
			text = "<null>";
		}
		for (int i = 0; i < text.length(); i++) {
			char ch = text.charAt(i);
			if (ch == '<') {
				sb.append("&lt;");
			} else if (ch == '>') {
				sb.append("&gt;");
			} else if (ch == '&') {
				sb.append("&amp;");
			} else {
				sb.append(ch);
			}
		}
	}

	public void appendISO8601(StringBuffer sb, long millis) {
		GregorianCalendar greg = new GregorianCalendar();
		greg.setTimeInMillis(millis);
//		Date date = new Date(millis);
		sb.append(greg.get(Calendar.YEAR));
		sb.append('-');
		a2(sb, greg.get(Calendar.MONTH) + 1);
		sb.append('-');
		a2(sb, greg.get(Calendar.DAY_OF_MONTH));
		sb.append(' ');
		a2(sb, greg.get(Calendar.HOUR_OF_DAY));
		sb.append(':');
		a2(sb, greg.get(Calendar.MINUTE));
		sb.append(':');
		a2(sb, greg.get(Calendar.SECOND));
	}

	private void a2(StringBuffer sb, int x) {
		if (x < 10) {
			sb.append('0');
		}
		sb.append(x);
	}
}
