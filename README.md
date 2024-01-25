> [!CAUTION]
> С 29.02.2024 разработка проекта завершена. Инструментарий будет поддерживаться в актуальном и рабочем состоянии, но
> правки в основной код вноситься не будут.

[![Code Smells][code_smells_badge]][code_smells_link]
[![Maintainability Rating][maintainability_rating_badge]][maintainability_rating_link]
[![Security Rating][security_rating_badge]][security_rating_link]
[![Bugs][bugs_badge]][bugs_link]
[![Vulnerabilities][vulnerabilities_badge]][vulnerabilities_link]
[![Duplicated Lines (%)][duplicated_lines_density_badge]][duplicated_lines_density_link]
[![Reliability Rating][reliability_rating_badge]][reliability_rating_link]
[![Quality Gate Status][quality_gate_status_badge]][quality_gate_status_link]
[![Technical Debt][technical_debt_badge]][technical_debt_link]
[![Lines of Code][lines_of_code_badge]][lines_of_code_link]

Код мода "Lion King" для Minecraft 1.6.4. Перенесён с Forge на Voldeloom и пригоден к разработке на текущий
момент. Содержит две ветки:

* main - исправленный оригинальный код (содержит четыре исправления).
    * Закомментирован фрагмент кода в LKTickHandlerClient, который перестал работать из-за инкапсуляции полей.
    * Закомментирован фрагмент кода в LKItemGroundRhinoHorn, который перестал работать из-за инкапсуляции полей.
    * Исправлена ошибка с файлом LKRenderBug (файл был перемещён из common в client).
    * Исправлена ошибка с файлом LKRenderSkeletalHyenaHead (файл был перемещён из common в client).
* refact - исправленный код, прошедший полный рефакторинг.

<!----------------------------------------------------------------------------->

[code_smells_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=code_smells
[code_smells_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[maintainability_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=sqale_rating
[maintainability_rating_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[security_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=security_rating
[security_rating_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[bugs_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=bugs
[bugs_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[vulnerabilities_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=vulnerabilities
[vulnerabilities_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[duplicated_lines_density_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=duplicated_lines_density
[duplicated_lines_density_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[reliability_rating_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=reliability_rating
[reliability_rating_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[quality_gate_status_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=alert_status
[quality_gate_status_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[technical_debt_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=sqale_index
[technical_debt_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
[lines_of_code_badge]: https://sonarcloud.io/api/project_badges/measure?project=Hummel009_Lion-King&metric=ncloc
[lines_of_code_link]: https://sonarcloud.io/summary/overall?id=Hummel009_Lion-King
