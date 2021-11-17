package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Account;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final String pathToFile;

    private static final String CLIENT_GROUP = "clientId";
    private static final String NUMBER_GROUP = "number";
    private static final String REGEX = "\\s*\\{\\s*\"clientId\": (?<" + CLIENT_GROUP + ">\\d+),\\s*\"number\": \"(?<" + NUMBER_GROUP + ">\\S+)\"\\s*}";

    /**
     * Метод позволяет получить набор объектов Account с номерами счетов по конткретному индентификатору клиента
     *
     * @param clientId идентификатор клиента
     * @return набор объектов Account
     * @throws IOException при возниконовении ограничений, таких как отсутствие файла и т.д.
     */
    @Override
    public Set<Account> getAllAccountsByClientId(long clientId) throws IOException {
        String fileContents = readFile();
        return parseJsonFile(fileContents, clientId);
    }

    /**
     * Метод используется для изменения номера счета клиента.
     *
     * @param clientId      идентификатор клиента
     * @param currentNumber текущий номер счета клиента
     * @param updateNumber  номер счета на который необходимо произвести замену
     * @return true - совпадение найдено, произведена замена; false - совпадения не найдено, замена не произведена
     */
    public boolean updateClientNumber(long clientId, String currentNumber, String updateNumber) throws IOException {
        String fileContents = readFile();
        StringBuilder sb = new StringBuilder(fileContents);
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(fileContents);
        while (matcher.find()) {
            // производится замена при совпадении входных 'clientId' и 'currentNumber'
            if (matcher.group(CLIENT_GROUP).equals(Long.toString(clientId)) && matcher.group(NUMBER_GROUP).equals(currentNumber)) {
                int start = matcher.start(NUMBER_GROUP);    // поиск начала вхождения номера счета
                int end = matcher.end(NUMBER_GROUP);        // поиск конца вхождения номера счета
                sb.replace(start, end, updateNumber);
                writeFile(sb.toString());                   // вызов метода записи изменений в файл
                return true;
            }
        }
        // не было найдено совпадений по 'clientId' и 'currentNumber'
        return false;
    }

    /**
     * Метод используется для парсинга json-файла по clientId
     *
     * @param fileContents содержимое файла для парсинга
     * @param clientId     идентификатор клиента
     * @return Набор всех Account содержащих банковские книжки (number), которыми указанный клиент
     */
    private Set<Account> parseJsonFile(String fileContents, long clientId) {
        Set<Account> accounts = new HashSet<>();
        Pattern pattern = Pattern.compile(REGEX);
        Matcher matcher = pattern.matcher(fileContents);
        while (matcher.find()) {
            long id;
            String number;
            // выбираем только совпадения по clientId
            if ((id = Long.parseLong(matcher.group(CLIENT_GROUP))) == clientId) {
                number = matcher.group(NUMBER_GROUP);
                accounts.add(new Account(id, number));
            }
        }
        return accounts;
    }

    /**
     * Метод используется для побайтового считывания любого текстового файла.
     * Нужен для внутреннего использования.
     *
     * @return String - содержимое файла
     * @throws IOException при возниконовении ограничений, таких как отсутствие файла и т.д.
     */
    private String readFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
            int symbol;
            while ((symbol = bufferedReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Метод производит запись в файл
     *
     * @param fileContents обновленная строка, заносимая в файл
     * @throws IOException если возникают проблемы с записью файл
     */
    private void writeFile(String fileContents) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pathToFile)))) {
            // производится запись строки в файл
            bufferedWriter.write(fileContents);
        }
    }
}