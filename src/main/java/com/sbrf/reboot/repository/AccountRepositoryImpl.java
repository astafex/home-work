package com.sbrf.reboot.repository;

import com.sbrf.reboot.dto.Account;
import com.sbrf.reboot.service.AccountRepository;
import lombok.RequiredArgsConstructor;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements AccountRepository {
    private final String pathToFile;

    private final String CLIENT_GROUP = "clientId";
    private final String NUMBER_GROUP = "number";
    private final String regex = "\\s*\\{\\s*\"clientId\": (?<" + CLIENT_GROUP + ">\\d+),\\s*\"number\": \"(?<" + NUMBER_GROUP + ">\\S+)\"\\s*}";

    @Override
    public Set<Account> getAllAccountsByClientId(long clientId) throws FileNotFoundException {
        String fileContents = readFile();
        return parseJsonFile(fileContents, clientId);
    }

    /** Метод используется для парсинга json-файла по clientId
     *
     * @param fileContents - содержимое файла для парсинга
     * @param clientId - идентификатор клиента
     * @return Набор всех Account содержащих банковские книжки (number), которыми указанный клиент
     */
    private Set<Account> parseJsonFile(String fileContents, long clientId) {
        Set<Account> accounts = new HashSet<>();
        Pattern pattern = Pattern.compile(regex);
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
     * @return null - нет доступа для считывания файла или иное IOException;
     * String - содержимое файла
     * @throws FileNotFoundException - если файла по переданному пути не существует
     */
    private String readFile() throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(pathToFile)))) {
            int symbol;
            while ((symbol = bufferedReader.read()) != -1) {
                stringBuilder.append((char) symbol);
            }
        } catch (IOException exception) {
            if (exception.getClass().equals(FileNotFoundException.class))
                throw new FileNotFoundException(String.format("Файл '%s' не найден", pathToFile));
            exception.printStackTrace();
            return null; // возвращается при любом IOException, кроме FileNotFoundException
        }
        return stringBuilder.toString();
    }
}
