create table if not exists products (id bigserial primary key, title varchar(255), price integer);

insert into products (title, price)
values
    ('Demon Souls', 100),
    ('Dark Souls', 80),
    ('Dark Souls2', 80),
    ('Dark Souls3', 80),
    ('BloodBorne', 90),
    ('Sekiro', 50),
    ('Elden Ring', 100),
    ('WoW', 100),
    ('WoW: Burning Crusade', 90),
    ('WoW: WOTLK', 90),
    ('WoW: Cataclysm', 90),
    ('WoW: MoP', 100),
    ('WoW: Draenor', 100),
    ('WoW: Legion', 100),
    ('WoW: BFA', 100),
    ('WoW: ShadowLands', 100),
    ('WoW: DragonFlight', 100),
    ('Diablo', 30),
    ('Diablo2', 40),
    ('Diablo3', 100);